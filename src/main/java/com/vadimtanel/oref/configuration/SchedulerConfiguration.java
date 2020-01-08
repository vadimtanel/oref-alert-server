package com.vadimtanel.oref.configuration;

import com.vadimtanel.oref.logger.ILogger;
import com.vadimtanel.oref.service.DataFetcherImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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

    @Scheduled(fixedDelayString = "${live.fixedDelay.in.milliseconds}")
    public void pollingOrefLiveStatus() {
        logger.Info(String.format("Scheduled started at: %s", getCurrentUTCTime()));
        dataFetcher.getData("https://www.oref.org.il/Shared/Ajax/GetAlarms.aspx?fromDate=" + getCurrentUTCTime());
        logger.Info(String.format("Scheduled finish at : %s", getCurrentUTCTime()));
    }

    private String getCurrentUTCTime(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date);
    }
}
