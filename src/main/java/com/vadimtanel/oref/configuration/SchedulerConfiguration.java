package com.vadimtanel.oref.configuration;

import com.vadimtanel.oref.dto.AlertDto;
import com.vadimtanel.oref.handler.DateTimeHandler;
import com.vadimtanel.oref.logger.ILogger;
import com.vadimtanel.oref.service.AlertDataFetcherImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
import java.util.stream.Collectors;

/*******************************************************************************
 *  Created by Vadim Tanel on 05/01/2020 21:59.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Configuration
@EnableScheduling
public class SchedulerConfiguration {
    @Autowired
    ILogger logger;

    @Autowired
    private AlertDataFetcherImpl dataFetcher;

    @Autowired
    DateTimeHandler dateTimeHandler;

    @Scheduled(fixedDelayString = "${live.fixedDelay.in.milliseconds}")
    public void pollingOrefLiveStatus() {
        logger.Info(String.format("Scheduled started at: %s", dateTimeHandler.getCurrentUTCTime()));
        List<AlertDto> result = dataFetcher.fetchNewAlerts();
        String alertListStr = result.stream().map( n -> n.toString() ).collect(Collectors.joining(","));
        logger.Info(String.format("Scheduled finish at : %s, with data: %s", dateTimeHandler.getCurrentUTCTime(), alertListStr));
    }
}
