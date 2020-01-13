package com.vadimtanel.oref.service;

import com.vadimtanel.oref.data.OrefUrl;
import com.vadimtanel.oref.handler.DateTimeHandler;
import com.vadimtanel.oref.handler.RestHandler;
import com.vadimtanel.oref.logger.ILogger;
import com.vadimtanel.oref.repository.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;


/*******************************************************************************
 *  Created by Vadim Tanel on 03/01/2020 0:42.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Service
public class DataFetcherImpl implements DataFetcher {
    @Autowired
    RestHandler restHandler;

    @Autowired
    ILogger logger;

    @Autowired
    DateTimeHandler dateTimeHandler;

    private DataAnalyzer dataAnalyzer;
    private AlertService alertService;

    public DataFetcherImpl(DataAnalyzer dataAnalyzer, AlertService alertService) {
        this.dataAnalyzer = dataAnalyzer;
        this.alertService = alertService;
    }

    @Override
    public List<Alert> getHistory(String fromDate) {
        return getHistory(fromDate, null);
    }

    @Override
    public List<Alert> getHistory(String fromDate, String toDate) {
        String url;
        if (toDate == null) {
            url = String.format(OrefUrl.HISTORY_FORM_DATE, fromDate);
        } else {
            url = String.format(OrefUrl.HISTORY_FORM_DATE_TO_DATE, fromDate, toDate);
        }
        String historyData = retrieveDataFromServer(url);
        List<Alert> alerts = dataAnalyzer.analyzeHistoryData(historyData);
        return alerts;
    }

    @Override
    public List<Alert> getLiveAlerts() {
        ResponseEntity<String> response = restHandler.get(OrefUrl.LIVE);
        if (response == null || response.getStatusCode() != HttpStatus.OK) {
            logger.Error("Error in DataFetcherImpl get Live data with response code: " + response.getStatusCode());
            return null;
        }
        String json = response.getBody().trim();
        List<Alert> alerts = dataAnalyzer.analyzeLiveAlerts(json);
        return alerts;
    }

    @Override
    public List<Alert> fetchNewAlerts() {
        List<Alert> alerts = getLiveAlerts();
        List<Alert> newAlerts = dataAnalyzer.filterNewAlerts(alerts);
        alertService.saveAlerts(newAlerts);
        return newAlerts;
    }

    private String retrieveDataFromServer(String url) {
        ResponseEntity<String> response = restHandler.get(url);
        if (response == null || response.getStatusCode() != HttpStatus.OK) {
            logger.Error("Error in DataFetcherImpl get History data with response code: " + response.getStatusCode());
            return null;
        }
        String body = response.getBody();
        return body;
    }
}
