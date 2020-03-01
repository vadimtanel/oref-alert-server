package com.vadimtanel.oref.handler;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

/*******************************************************************************
 *  Created by Vadim Tanel on 05/01/2020 23:43.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

public interface RestHandler {
    ResponseEntity<String> exchange(String url, HttpMethod method, MultiValueMap<String, String> headerMap, Object body);
    ResponseEntity<String> exchangeJson(String url, HttpMethod method, MultiValueMap<String, String> headerMap, Object body);

    ResponseEntity<String> getAsChrome(String url);
    ResponseEntity<String> get(String url);
    ResponseEntity<String> get(String url, MultiValueMap<String, String> headerMap);
    ResponseEntity<String> getJson(String url);
    ResponseEntity<String> getJson(String url, MultiValueMap<String, String> headerMap);

    ResponseEntity<String> post(String url, Object body);
    ResponseEntity<String> post(String url, Object body, MultiValueMap<String, String> headerMap);
    ResponseEntity<String> postJson(String url, Object body);
    ResponseEntity<String> postJson(String url, Object body, MultiValueMap<String, String> headerMap);
}
