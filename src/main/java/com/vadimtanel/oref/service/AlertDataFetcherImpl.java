package com.vadimtanel.oref.service;

import com.vadimtanel.oref.data.OrefUrl;
import com.vadimtanel.oref.dto.AlertDto;
import com.vadimtanel.oref.dto.GeoPositionDto;
import com.vadimtanel.oref.handler.DateTimeHandler;
import com.vadimtanel.oref.handler.RestHandler;
import com.vadimtanel.oref.logger.ILogger;
import com.vadimtanel.oref.repository.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


/*******************************************************************************
 *  Created by Vadim Tanel on 03/01/2020 0:42.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Service
public class AlertDataFetcherImpl implements AlertDataFetcher {
    @Autowired
    RestHandler restHandler;

    @Autowired
    ILogger logger;

    @Autowired
    DateTimeHandler dateTimeHandler;

    private AlertDataAnalyzer alertDataAnalyzer;
    private AlertService alertService;
    private GeoPositionService geoPositionService;

    public AlertDataFetcherImpl(AlertDataAnalyzer alertDataAnalyzer, AlertService alertService, GeoPositionService geoPositionService) {
        this.alertDataAnalyzer = alertDataAnalyzer;
        this.alertService = alertService;
        this.geoPositionService = geoPositionService;
    }

    @Override
    public List<AlertDto> getHistory(String fromDate) {
        return getHistory(fromDate, null);
    }

    @Override
    public List<AlertDto> getHistory(String fromDate, String toDate) {
        String url;
        if (toDate == null) {
            url = String.format(OrefUrl.HISTORY_FORM_DATE, fromDate);
        } else {
            url = String.format(OrefUrl.HISTORY_FORM_DATE_TO_DATE, fromDate, toDate);
        }
        String historyData = retrieveDataFromServer(url);
        List<AlertDto> alerts = alertDataAnalyzer.analyzeHistoryData(historyData);
        return alerts;
    }

    @Override
    public List<AlertDto> getLiveAlerts() {
        ResponseEntity<String> response = restHandler.get(OrefUrl.LIVE);
        if (response == null || response.getStatusCode() != HttpStatus.OK) {
            logger.Error("Error in DataFetcherImpl get Live data with response code: " + response.getStatusCode());
            return null;
        }
        String json = response.getBody().trim();
        List<AlertDto> alerts = alertDataAnalyzer.analyzeLiveAlerts(json);
        return alerts;
    }

    @Override
    public List<AlertDto> fetchNewAlerts() {
        List<AlertDto> alerts = getLiveAlerts();
        List<AlertDto> newAlerts = alertDataAnalyzer.filterNewAlerts(alerts);
        List<AlertDto> alertsWithGeoPosition = addGeoPosition(newAlerts);
        alertService.saveAlerts(alertsWithGeoPosition);
        return newAlerts;
    }

    private List<AlertDto> addGeoPosition(List<AlertDto> alerts) {
        for (AlertDto alert: alerts) {
            GeoPositionDto geoPositionDto = geoPositionService.getGeoPosition(alert.getLocation());
            alert.setGeoPosition(geoPositionDto);
        }
        return alerts;
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
