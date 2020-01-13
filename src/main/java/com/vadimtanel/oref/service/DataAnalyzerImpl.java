package com.vadimtanel.oref.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vadimtanel.oref.handler.DateTimeHandler;
import com.vadimtanel.oref.logger.ILogger;
import com.vadimtanel.oref.repository.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*******************************************************************************
 *  Created by Vadim Tanel on 10/01/2020 21:40.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Service
public class DataAnalyzerImpl implements DataAnalyzer {
    @Autowired
    ILogger logger;

    @Autowired
    DateTimeHandler dateTimeHandler;

    private XmlConvector xmlConvector;
    private long lastAlarmTimeStamp;

    public DataAnalyzerImpl(XmlConvector xmlConvector){
        this.xmlConvector = xmlConvector;
        lastAlarmTimeStamp = 0;
    }

    @Override
    public List<Alert> analyzeLiveAlerts(String data) {
        List<Alert> results = new ArrayList<>();
        JsonElement jsonElement = JsonParser.parseString(data);
        if (!jsonElement.isJsonNull() && jsonElement.isJsonArray()) {
            for (JsonElement itemAlert: jsonElement.getAsJsonArray()) {
                Alert alert = parseJsonLiveToAlert(itemAlert);
                results.add(alert);
            }
        }
        return results;
    }

    @Override
    public List<Alert> filterNewAlerts(List<Alert> alerts) {
        Stream<Alert> streamNewAlerts = alerts.stream().filter(alert -> alert.getTimeStamp() > lastAlarmTimeStamp);
        Alert lastAlert = streamNewAlerts.max(Comparator.comparing(Alert::getTimeStamp)).get();

        lastAlarmTimeStamp = lastAlert.getTimeStamp();

        List<Alert> newAlerts = streamNewAlerts.collect(Collectors.toList());

        String alertListStr = newAlerts.stream().map( n -> n.toString() ).collect(Collectors.joining(","));
        logger.Info(String.format("Found new alerts: [%s]", alertListStr));

        return newAlerts;
    }

    @Override
    public List<Alert> analyzeHistoryData(String historyData) {
        String bodyTrim = historyData.trim();
        String json = xmlConvector.toJson(bodyTrim);
        List<Alert> alerts = analyzeHistoryAlert(json);
        return alerts;
    }

    private List<Alert> analyzeHistoryAlert(String json) {
        List<Alert> results = new ArrayList<>();
        JsonElement jsonElement = JsonParser.parseString(json);
        if (!jsonElement.isJsonNull() && jsonElement.isJsonArray()) {
            for (JsonElement itemAlert: jsonElement.getAsJsonArray()) {
                Alert alert = parseJsonHistoryToAlert(itemAlert);
                results.add(alert);
            }
        }
        return results;
    }

    private Alert parseJsonHistoryToAlert(JsonElement itemAlert) {
        JsonObject itemAlertObj = itemAlert.getAsJsonObject();
        String title = "Oref history alert";
        String city = itemAlertObj.get("city").getAsString();
        String date = itemAlertObj.get("date").getAsString();
        String time = itemAlertObj.get("time").getAsString();
        String alertDateStr = String.format("%s %s", date, time);
        SimpleDateFormat dateFormat = dateTimeHandler.formattedHistory();

        Date alertDate = null;
        try {
            alertDate = dateFormat.parse(alertDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.Error("analyzeLiveAlerts ParseException of: " + alertDateStr + " \r\n" + e.toString());
        }

        long timeMilliSec = alertDate.getTime();

        return new Alert(timeMilliSec, title, city, date, time + ":00");
    }

    private Alert parseJsonLiveToAlert(JsonElement itemAlert) {
        JsonObject itemAlertObj = itemAlert.getAsJsonObject();
        String alertDateStr = itemAlertObj.get("alertDate").getAsString();
        SimpleDateFormat dateFormat = dateTimeHandler.formattedLive();

        Date alertDate = null;
        try {
            alertDate = dateFormat.parse(alertDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            logger.Error("analyzeLiveAlerts ParseException of: " + alertDateStr + " \r\n" + e.toString());
        }

        long timeMilliSec = alertDate.getTime();
        String title = itemAlertObj.get("title").getAsString();
        String city = itemAlertObj.get("data").getAsString();
        String[] alertDataArr = alertDateStr.split(" ");
        String date = alertDataArr[0];
        String time = alertDataArr[1];
        return new Alert(timeMilliSec, title, city, date, time);
    }
}
