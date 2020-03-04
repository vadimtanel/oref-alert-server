package com.vadimtanel.oref.service;

import com.vadimtanel.oref.data.OrefUrl;
import com.vadimtanel.oref.dto.AlertDto;
import com.vadimtanel.oref.dto.GeoPositionDto;
import com.vadimtanel.oref.handler.DateTimeHandler;
import com.vadimtanel.oref.handler.RestHandler;
import com.vadimtanel.oref.logger.ILogger;
import com.vadimtanel.oref.service.geoPosition.GeoPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.List;


/*******************************************************************************
 *  Created by Vadim Tanel on 03/01/2020 0:42.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Service
public class AlertDataFetcherImpl implements AlertDataFetcher {
    @Autowired
    private RestHandler restHandler;

    @Autowired
    private ILogger logger;

    @Autowired
    private DateTimeHandler dateTimeHandler;

    private final AlertDataAnalyzer alertDataAnalyzer;
    private final AlertService alertService;
    private final GeoPositionService geoPositionService;

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
        if (historyData == null) {
            return new ArrayList<>();
        }
        List<AlertDto> alerts = alertDataAnalyzer.analyzeHistoryData(historyData);
        List<AlertDto> alertsWithGeoPosition = addGeoPosition(alerts);
        return alertsWithGeoPosition;
    }

    @Override
    public List<AlertDto> getLiveAlerts() {
        ResponseEntity<String> response = restHandler.get(OrefUrl.LIVE);
        if (response == null || response.getStatusCode() != HttpStatus.OK) {
            String responseCode = response != null ? response.getStatusCode().toString() : "No response";
            logger.Error("Error in DataFetcherImpl get Live data with response code: " + responseCode);
            return new ArrayList<>();
        }
        String json = response.getBody().trim();
        List<AlertDto> alerts = alertDataAnalyzer.analyzeLiveAlerts(json);
        List<AlertDto> alertsWithGeoPosition = addGeoPosition(alerts);
        return alertsWithGeoPosition;
    }

    @Override
    public List<AlertDto> fetchNewAlerts() {
        List<AlertDto> alerts = getLiveAlerts();
        List<AlertDto> newAlerts = alertDataAnalyzer.filterNewAlerts(alerts);
        alertService.saveAlerts(newAlerts);
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
        ResponseEntity<String> response = restHandler.getAsChrome(url);
        if (response == null || response.getStatusCode() != HttpStatus.OK) {
            String error = "Error in DataFetcherImpl get History data with response code: ";
            if (response != null) {
                error += response.getStatusCode();
            }
            logger.Error(error);
            return null;
        }
        String body = response.getBody();
        return body;
    }
}
