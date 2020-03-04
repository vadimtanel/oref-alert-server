package com.vadimtanel.oref.service.geoPosition;

import com.vadimtanel.oref.dto.GeoPositionDto;
import com.vadimtanel.oref.handler.RestHandler;
import com.vadimtanel.oref.logger.ILogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/*******************************************************************************
 *  Created by Vadim Tanel on 11/02/2020 9:32.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Service
public class GeoPositionFetcherImpl implements GeoPositionFetcher {
    @Autowired
    private RestHandler restHandler;

    @Autowired
    private ILogger logger;

    private GeoPositionAnalyzer geoPositionAnalyzer;

    public GeoPositionFetcherImpl(GeoPositionAnalyzer geoPositionAnalyzer) {
        this.geoPositionAnalyzer = geoPositionAnalyzer;
    }

    @Override
    public GeoPositionDto getGeoPosition(String cityName) {
        String jsonData = fetchGeoPositionData(cityName);
        if (jsonData == null) {
            return null;
        }

        GeoPositionDto geoPositionDto = geoPositionAnalyzer.analyzeGeoPositionData(jsonData);
        if (geoPositionDto == null) {
            return null;
        }
        geoPositionDto.setLocation(cityName);
        return geoPositionDto;
    }


    public String fetchGeoPositionData(String cityName) {
        String url = geoPositionAnalyzer.getUrl(cityName);
        ResponseEntity<String> response = restHandler.get(url);
        if (response == null || response.getStatusCode() != HttpStatus.OK) {
            String responseCode = response != null ? response.getStatusCode().toString() : "";
            logger.Error("Error in GeoPositionFetcherImpl get geo position data with response code: " + responseCode);
            return null;
        }
        String body = response.getBody();
        return body;
    }
}
