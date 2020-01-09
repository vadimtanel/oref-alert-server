package com.vadimtanel.oref.service;

/*******************************************************************************
 *  Created by Vadim Tanel on 08/01/2020 8:34.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

public interface DataFetcher {
    String getHistory(String fromDate);

    String getHistory(String fromDate, String toDate);

    String getLive();
}
