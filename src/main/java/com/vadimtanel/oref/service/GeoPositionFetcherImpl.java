package com.vadimtanel.oref.service;

import com.vadimtanel.oref.data.GeoCodeUrl;
import com.vadimtanel.oref.dto.GeoPositionDto;
import com.vadimtanel.oref.handler.RestHandler;
import com.vadimtanel.oref.logger.ILogger;
import com.vadimtanel.oref.repository.GeoPosition;
import com.vadimtanel.oref.repository.GeoPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/*******************************************************************************
 *  Created by Vadim Tanel on 02/02/2020 16:25.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Service
public class GeoPositionFetcherImpl implements GeoPositionFetcher {
    @Autowired
    RestHandler restHandler;

    @Autowired
    ILogger logger;

    private GeoPositionAnalyzer geoPositionAnalyzer;

    public GeoPositionFetcherImpl(GeoPositionAnalyzer geoPositionAnalyzer) {
        this.geoPositionAnalyzer = geoPositionAnalyzer;
    }

    @Override
    public GeoPositionDto getGeoPosition(String cityName) {
        String jsonData = fetchGeoPositionData(cityName);
        GeoPositionDto geoPositionDto = geoPositionAnalyzer.analyzeGeoPositionData(jsonData);
        return geoPositionDto;
    }

    public String fetchGeoPositionData(String cityName) {
        String url = String.format(GeoCodeUrl.CITY_TO_LANG_LATT, cityName);
        ResponseEntity<String> response = restHandler.get(url);
        if (response == null || response.getStatusCode() != HttpStatus.OK) {
            logger.Error("Error in GeoPositionFetcherImpl get geo position data with response code: " + response.getStatusCode());
            return null;
        }
        String body = response.getBody();
        return body;
    }
}
