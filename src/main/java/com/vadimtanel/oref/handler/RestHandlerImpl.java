package com.vadimtanel.oref.handler;

import com.vadimtanel.oref.logger.ILogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/*******************************************************************************
 *  Created by Vadim Tanel on 05/01/2020 23:37.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Component
public class RestHandlerImpl implements RestHandler {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ILogger logger;

    public RestHandlerImpl(){

    }

    public ResponseEntity<String> exchange(String url, HttpMethod method, MultiValueMap<String, String> headerMap, Object body) {
        ResponseEntity<String> response = null;
        try {
            HttpHeaders headers = new HttpHeaders(headerMap);

            HttpEntity entity = body == null ? new HttpEntity(headers) : new HttpEntity(body, headers);

            response = restTemplate.exchange(url, method, entity, String.class);
            String log = String.format("httpMethod: %s, url: %s, response-status-code: %s, response-body: %s", method, url, response.getStatusCode(), response.getBody());
            logger.Info(log);
        } catch (Exception ex) {
            logger.Sever(ex.getMessage());
        }
        return response;
    }

    @Override
    public ResponseEntity<String> exchangeJson(String url, HttpMethod method, MultiValueMap<String, String> headerMap, Object body) {
        headerMap.set("Content-Type", "application/json");
        return exchange(url, method, headerMap, body);
    }

    //region Get methods
    @Override
    public ResponseEntity<String> get(String url) {
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>();
        return get(url, headerMap);
    }

    @Override
    public ResponseEntity<String> get(String url, MultiValueMap<String, String> headerMap) {
        ResponseEntity<String> response = exchange(url, HttpMethod.GET, headerMap, null);
        return response;
    }


    @Override
    public ResponseEntity<String> getJson(String url) {
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>();
        return getJson(url, headerMap);
    }


    @Override
    public ResponseEntity<String> getJson(String url, MultiValueMap<String, String> headerMap) {
        headerMap.set("Content-Type", "application/json");
        return get(url, headerMap);
    }
    //endregion

    //region Post methods
    @Override
    public ResponseEntity<String> post(String url, Object body) {
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>();
        return post(url, body, headerMap);
    }

    @Override
    public ResponseEntity<String> post(String url, Object body, MultiValueMap<String, String> headerMap) {
        ResponseEntity<String> response = exchange(url, HttpMethod.POST, headerMap, body);
        return response;
    }

    @Override
    public ResponseEntity<String> postJson(String url, Object body) {
        MultiValueMap<String, String> headerMap = new LinkedMultiValueMap<String, String>();
        return postJson(url, body, headerMap);
    }

    @Override
    public ResponseEntity<String> postJson(String url, Object body, MultiValueMap<String, String> headerMap) {
        headerMap.set("Content-Type", "application/json");
        return post(url, body, headerMap);
    }
    //endregion
}
