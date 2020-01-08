package com.vadimtanel.oref.service;

import com.vadimtanel.oref.handler.RestHandler;
import com.vadimtanel.oref.logger.ILogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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

    private XmlConvector xmlConvector;

    public DataFetcherImpl(XmlConvector xmlConvector) {
        this.xmlConvector = xmlConvector;
    }

    public String getData(String path) {
        ResponseEntity<String> response = restHandler.get(path);
        String body = response.getBody();
        String json = xmlConvector.toJson(body.trim());
        return json;
    }
}
