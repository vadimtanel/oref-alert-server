package com.vadimtanel.oref.service;

import com.vadimtanel.oref.repository.Alert;

import java.util.List;

/*******************************************************************************
 *  Created by Vadim Tanel on 08/01/2020 8:34.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

public interface DataFetcher {
    List<Alert> getHistory(String fromDate);

    List<Alert> fetchNewAlerts();

    List<Alert> getHistory(String fromDate, String toDate);

    List<Alert> getLiveAlerts();
}
