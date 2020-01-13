package com.vadimtanel.oref.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;

/*******************************************************************************
 *  Created by Vadim Tanel on 03/01/2020 0:47.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Component
public class SpringConfig {

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Logger getLogger() {
        return LoggerFactory.getLogger("vadimtanel-oref");
    }
}