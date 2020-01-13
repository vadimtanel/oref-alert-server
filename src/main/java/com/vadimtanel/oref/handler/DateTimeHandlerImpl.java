package com.vadimtanel.oref.handler;

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
    public long getCurrentEpoch() {
        return Instant.now().toEpochMilli();
    }

    @Autowired
    public String getCurrentUTCTime(){
        SimpleDateFormat dateFormat = formattedLive();
        dateFormat.setTimeZone(TimeZone.getTimeZone("Israel"));
        return dateFormat.format(new Date());
    }

    @Override
    public SimpleDateFormat formattedDateDots() {
        return new SimpleDateFormat("dd.MM.yyyy");
    }

    @Autowired
    public SimpleDateFormat formattedHistory() {
        return new SimpleDateFormat("dd.MM.yyyy HH:mm");
    }

    @Autowired
    public SimpleDateFormat formattedLive() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }
}
