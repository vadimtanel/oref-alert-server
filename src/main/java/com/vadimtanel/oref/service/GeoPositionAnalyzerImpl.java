package com.vadimtanel.oref.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vadimtanel.oref.dto.GeoPositionDto;
import com.vadimtanel.oref.logger.ILogger;
import com.vadimtanel.oref.service.GeoPositionAnalyzer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

/*******************************************************************************
 *  Created by Vadim Tanel on 02/02/2020 18:56.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Service
public class GeoPositionAnalyzerImpl implements GeoPositionAnalyzer {
    @Autowired
    ILogger logger;

    public GeoPositionAnalyzerImpl() {
    }

    @Override
    public GeoPositionDto analyzeGeoPositionData(String jsonData) {
        JsonElement jsonElement = JsonParser.parseString(jsonData);
        JsonObject itemObj = jsonElement.getAsJsonObject();
        if (itemObj.getAsJsonObject("error") != null) {
            return null;
        }

        JsonObject standardObj = itemObj.getAsJsonObject("standard");
        String city = standardObj.get("city").getAsString();
        String latt = itemObj.get("latt").getAsString();
        String longt = itemObj.get("longt").getAsString();
        GeoPositionDto geoPositionDto = new GeoPositionDto(city, Double.parseDouble(latt), Double.parseDouble(longt));
        return geoPositionDto;
    }
}
