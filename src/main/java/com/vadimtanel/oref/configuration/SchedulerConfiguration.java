package com.vadimtanel.oref.configuration;

import com.vadimtanel.oref.handler.DateTimeHandler;
import com.vadimtanel.oref.logger.ILogger;
import com.vadimtanel.oref.service.DataFetcherImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

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
    private DataFetcherImpl dataFetcher;

    @Autowired
    DateTimeHandler dateTimeHandler;

    @Scheduled(fixedDelayString = "${live.fixedDelay.in.milliseconds}")
    public void pollingOrefLiveStatus() {
        logger.Info(String.format("Scheduled started at: %s", dateTimeHandler.getCurrentUTCTime()));
        dataFetcher.getHistory(dateTimeHandler.getCurrentUTCTime());
        logger.Info(String.format("Scheduled finish at : %s", dateTimeHandler.getCurrentUTCTime()));
    }
}
