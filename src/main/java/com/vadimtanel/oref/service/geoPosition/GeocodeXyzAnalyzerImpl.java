package com.vadimtanel.oref.service.geoPosition;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.vadimtanel.oref.data.GeoCodeUrl;
import com.vadimtanel.oref.dto.GeoPositionDto;
import com.vadimtanel.oref.logger.ILogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/*******************************************************************************
 *  Created by Vadim Tanel on 02/02/2020 18:56.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Profile("GeocodeXyz")
@Service
public class GeocodeXyzAnalyzerImpl implements GeoPositionAnalyzer {
    @Autowired
    private ILogger logger;

    public GeocodeXyzAnalyzerImpl() {
    }

    @Override
    public String getUrl(String location) {
        return String.format(GeoCodeUrl.CITY_TO_LANG_LATT, location);
    }

    @Override
    public GeoPositionDto analyzeGeoPositionData(String jsonData) {
        GeoPositionDto geoPositionDto = null;

        try {
            JsonElement jsonElement = JsonParser.parseString(jsonData);
            JsonObject itemObj = jsonElement.getAsJsonObject();
            if (itemObj.getAsJsonObject("error") != null) {
                return null;
            }

            JsonObject standardObj = itemObj.getAsJsonObject("standard");
            String city = standardObj.get("city").getAsString();
            String latt = itemObj.get("latt").getAsString();
            String longt = itemObj.get("longt").getAsString();
            geoPositionDto = new GeoPositionDto(city, Double.parseDouble(latt), Double.parseDouble(longt));
        } catch (JsonSyntaxException ex) {
            logger.Error("JsonSyntaxException occurred in HereapiComAnalyzerImpl.analyzeGeoPositionData: " + ex);
        } catch (Exception ex) {
            logger.Error("Exception occurred in HereapiComAnalyzerImpl.analyzeGeoPositionData: " + ex);
        }
        return geoPositionDto;
    }
}
