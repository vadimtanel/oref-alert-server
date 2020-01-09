package com.vadimtanel.oref.service;

import com.vadimtanel.oref.data.OrefUrl;
import com.vadimtanel.oref.handler.RestHandler;
import com.vadimtanel.oref.logger.ILogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;


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
    SimpleDateFormat simpleDateFormat;

    private XmlConvector xmlConvector;

    public DataFetcherImpl(XmlConvector xmlConvector) {
        this.xmlConvector = xmlConvector;
    }

    @Override
    public String getHistory(String fromDate) {
        return getHistory(fromDate, null);
    }

    @Override
    public String getHistory(String fromDate, String toDate) {
        try {
            String url;
            if (toDate == null) {
                url = String.format(OrefUrl.HISTORY_FORM_DATE, simpleDateFormat.parse(fromDate));
            } else {
                url = String.format(OrefUrl.HISTORY_FORM_DATE_TO_DATE, simpleDateFormat.parse(fromDate), simpleDateFormat.parse(toDate));
            }
            return retrieveDataFromServer(url);
        } catch (ParseException e) {
            logger.Error("Invalid Date conversion: " + e.getStackTrace().toString());
        }
        return null;
    }

    @Override
    public String getLive() {
        ResponseEntity<String> response = restHandler.get(OrefUrl.LIVE);
        if (response == null || response.getStatusCode() != HttpStatus.OK) {
            logger.Error("Error in DataFetcherImpl get Live data with response code: " + response.getStatusCode());
            return null;
        }
        return response.getBody();
    }

    private String retrieveDataFromServer(String url) {
        ResponseEntity<String> response = restHandler.get(url);
        String json = convertResponseToJson(response);
        return json;
    }

    private String convertResponseToJson(ResponseEntity<String> response) {
        if (response == null || response.getStatusCode() != HttpStatus.OK) {
            logger.Error("Error in DataFetcherImpl get History data with response code: " + response.getStatusCode());
            return null;
        }
        String body = response.getBody();
        String bodyTrim = body.trim();
        logger.Info("Data received: " + bodyTrim);
        String json = xmlConvector.toJson(bodyTrim);
        logger.Info("json result: " + json);
        return json;
    }
}
