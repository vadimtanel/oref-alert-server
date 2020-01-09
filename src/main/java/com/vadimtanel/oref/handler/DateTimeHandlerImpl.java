package com.vadimtanel.oref.handler;

import com.vadimtanel.oref.service.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

/*******************************************************************************
 *  Created by Vadim Tanel on 07/01/2020 9:08.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Component
public class DateTimeHandlerImpl implements DateTimeHandler {
    @Autowired
    SimpleDateFormat dateFormat;

    public long getCurrentEpoch() {
        return Instant.now().toEpochMilli();
    }

    public String getCurrentUTCTime(){
        dateFormat.setTimeZone(TimeZone.getTimeZone("Israel"));
        return dateFormat.format(new Date());
    }
}
