package com.vadimtanel.oref.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.vadimtanel.oref.logger.ILogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/*******************************************************************************
 *  Created by Vadim Tanel on 03/01/2020 21:56.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Service
public class XmlConvectorImpl implements XmlConvector {
    @Autowired
    ILogger logger;

    @Override
    public String toJson(String xml) {
        logger.Info("Data received as xml: " + xml);
        String[] xmlRows = xml.trim().split("\r\n");
        Object[] data = Arrays.stream(xmlRows)
                                .filter(x -> x.contains("<span"))
                                .map(x -> x.trim())
                                .toArray();

        JsonArray jsonArray = new JsonArray();
        for (int i=0; i< data.length; i+=3) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("date", data[i].toString().replace("<span><strong>","").replace("</strong></span>",""));
            jsonObject.addProperty("time", data[i+1].toString().replace("<span class=\"border\"><strong>","").replace("</strong></span>",""));
            jsonObject.addProperty("city", data[i+2].toString().replace("<span class=\"span_area\">","").replace("</span>",""));
            jsonArray.add(jsonObject);
        }

        String json = jsonArray.toString();
        logger.Info("XmlConvectorImpl json result: " + json);
        return json;
    }
}
