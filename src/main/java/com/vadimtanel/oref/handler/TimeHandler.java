package com.vadimtanel.oref.handler;

import org.springframework.stereotype.Component;

import java.time.Instant;

/*******************************************************************************
 *  Created by Vadim Tanel on 07/01/2020 9:08.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Component
public class TimeHandler {
    public long getCurrentEpoch() {
        return Instant.now().toEpochMilli();
    }
}
