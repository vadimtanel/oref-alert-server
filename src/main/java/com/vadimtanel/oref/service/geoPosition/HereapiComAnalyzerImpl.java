package com.vadimtanel.oref.service.geoPosition;

import com.google.gson.*;
import com.vadimtanel.oref.data.HereapiComUrl;
import com.vadimtanel.oref.dto.GeoPositionDto;
import com.vadimtanel.oref.logger.ILogger;
import com.vadimtanel.oref.service.apiKey.ApiKeyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/*******************************************************************************
 *  Created by Vadim Tanel on 12/02/2020 1:12.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Profile("HereapiCom")
@Service
public class HereapiComAnalyzerImpl implements GeoPositionAnalyzer {
    @Autowired
    private ILogger logger;

    private final ApiKeyService apiKeyService;

    private String apiKey;

    public HereapiComAnalyzerImpl(ApiKeyService apiKeyService) {
        this.apiKeyService = apiKeyService;
    }

    @PostConstruct
    private void initPostConstruct(){
        apiKey = getApiKey();
    }

    @Override
    public String getUrl(String location) {
        return String.format(HereapiComUrl.CITY_TO_LANG_LATT, apiKey, location);
    }

    private String getApiKey() {
        if (apiKey == null) {
            return apiKeyService.getApiKey("HereapiCom");
        }
        return apiKey;
    }

    @Override
    public GeoPositionDto analyzeGeoPositionData(String jsonData) {
        GeoPositionDto geoPositionDto = null;

        try {
            JsonElement jsonElement = JsonParser.parseString(jsonData);
            JsonObject itemObj = jsonElement.getAsJsonObject();
            JsonObject response = itemObj.get("Response").getAsJsonObject();
            JsonArray view = response.getAsJsonArray("View");
            if (view.size() == 0) {
                return null;
            }

            JsonObject view1Obj = view.get(0).getAsJsonObject();
            JsonObject resultObj = view1Obj.getAsJsonArray("Result").get(0).getAsJsonObject();
            JsonObject locationObj = resultObj.get("Location").getAsJsonObject();
            JsonObject navigationObj = locationObj.getAsJsonArray("NavigationPosition").get(0).getAsJsonObject();
            double latitude = navigationObj.get("Latitude").getAsDouble();
            double longitude = navigationObj.get("Longitude").getAsDouble();

            geoPositionDto = new GeoPositionDto("", latitude, longitude);
        } catch (JsonSyntaxException ex) {
            logger.Error("JsonSyntaxException occurred in HereapiComAnalyzerImpl.analyzeGeoPositionData: " + ex);
        } catch (Exception ex) {
            logger.Error("Exception occurred in HereapiComAnalyzerImpl.analyzeGeoPositionData: " + ex);
        }
        return geoPositionDto;
    }
}
