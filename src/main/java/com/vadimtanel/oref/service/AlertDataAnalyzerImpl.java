package com.vadimtanel.oref.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vadimtanel.oref.dto.AlertDto;
import com.vadimtanel.oref.handler.DateTimeHandler;
import com.vadimtanel.oref.logger.ILogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/*******************************************************************************
 *  Created by Vadim Tanel on 10/01/2020 21:40.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Service
public class AlertDataAnalyzerImpl implements AlertDataAnalyzer {
    @Autowired
    ILogger logger;

    @Autowired
    DateTimeHandler dateTimeHandler;

    private XmlConvector xmlConvector;
    private long lastAlarmTimeStamp;

    public AlertDataAnalyzerImpl(XmlConvector xmlConvector){
        this.xmlConvector = xmlConvector;
        lastAlarmTimeStamp = 0;
    }

    @Override
    public List<AlertDto> analyzeLiveAlerts(String data) {
        List<AlertDto> results = new ArrayList<>();
        JsonElement jsonElement = JsonParser.parseString(data);
        if (!jsonElement.isJsonNull() && jsonElement.isJsonArray()) {
            for (JsonElement itemAlert: jsonElement.getAsJsonArray()) {
                AlertDto alert = parseJsonLiveToAlert(itemAlert);
                if (alert.getLocation() != "בדיקה") {
                    results.add(alert);
                }
            }
        }
        return results;
    }

    @Override
    public List<AlertDto> filterNewAlerts(List<AlertDto> alerts) {
        if (alerts.size() == 0) {
            return alerts;
        }

        List<AlertDto> newAlerts = alerts.stream().filter(alert -> alert.getTimeStamp() > lastAlarmTimeStamp).collect(Collectors.toList());

        if (newAlerts.size() == 0) {
            return newAlerts;
        }
        AlertDto lastAlert = newAlerts.stream().max(Comparator.comparingLong(AlertDto::getTimeStamp)).get();
        lastAlarmTimeStamp = lastAlert.getTimeStamp();

        String alertListStr = newAlerts.stream().map( n -> n.toString() ).collect(Collectors.joining(","));
        logger.Info(String.format("Found new alerts: [%s]", alertListStr));

        return newAlerts;
    }

    @Override
    public List<AlertDto> analyzeHistoryData(String historyData) {
        String bodyTrim = historyData.trim();
        String json = xmlConvector.toJson(bodyTrim);
        List<AlertDto> alerts = analyzeHistoryAlert(json);
        return alerts;
    }

    private List<AlertDto> analyzeHistoryAlert(String json) {
        List<AlertDto> results = new ArrayList<>();
        JsonElement jsonElement = JsonParser.parseString(json);
        if (!jsonElement.isJsonNull() && jsonElement.isJsonArray()) {
            for (JsonElement itemAlert: jsonElement.getAsJsonArray()) {
                List<AlertDto> alerts = parseJsonHistoryToAlert(itemAlert);
                results.addAll(alerts);
            }
        }
        return results;
    }

    private List<AlertDto> parseJsonHistoryToAlert(JsonElement itemAlert) {
        JsonObject itemAlertObj = itemAlert.getAsJsonObject();
        String title = "Oref history alert";
        String city = itemAlertObj.get("city").getAsString();
        String date = itemAlertObj.get("date").getAsString();
        String time = itemAlertObj.get("time").getAsString();

        String alertDateStr = String.format("%s %s", date, time);
        SimpleDateFormat dateFormat = dateTimeHandler.formattedHistory();
        long timeMilliSec = getTimeMilliSec(alertDateStr, dateFormat);
        String[] cities = city.split(",");
        List<AlertDto> alertDtos = new ArrayList<>();
        for (String singleCity: cities) {
            if (singleCity == "בדיקה") {
                continue;
            }
            AlertDto alert = new AlertDto(timeMilliSec, title, singleCity, date, time + ":00");
            alertDtos.add(alert);
        }
        return alertDtos;
    }

    private AlertDto parseJsonLiveToAlert(JsonElement itemAlert) {
        JsonObject itemAlertObj = itemAlert.getAsJsonObject();
        String alertDateStr = itemAlertObj.get("alertDate").getAsString();
        String title = itemAlertObj.get("title").getAsString();
        String city = itemAlertObj.get("data").getAsString();

        String[] alertDataArr = alertDateStr.split(" ");
        String date = alertDataArr[0];
        String time = alertDataArr[1];
        SimpleDateFormat dateFormat = dateTimeHandler.formattedLive();

        long timeMilliSec = getTimeMilliSec(alertDateStr, dateFormat);

        return new AlertDto(timeMilliSec, title, city, date, time);
    }

    private long getTimeMilliSec(String alertDateStr, SimpleDateFormat dateFormat) {
        long timeMilliSec = 0;
        try {
            Date alertDate = dateFormat.parse(alertDateStr);
            timeMilliSec = alertDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            logger.Error("analyzeLiveAlerts ParseException of: " + alertDateStr + " \r\n" + e.toString());
        }
        return timeMilliSec;
    }
}
