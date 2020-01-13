package com.vadimtanel.oref.handler;

import java.text.SimpleDateFormat;

/*******************************************************************************
 *  Created by Vadim Tanel on 09/01/2020 9:47.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

public interface DateTimeHandler {
    long getCurrentEpoch();

    String getCurrentUTCTime();

    SimpleDateFormat formattedDateDots();

    SimpleDateFormat formattedHistory();

    SimpleDateFormat formattedLive();
}
