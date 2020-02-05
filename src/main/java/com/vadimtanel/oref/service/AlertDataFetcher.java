package com.vadimtanel.oref.service;

import com.vadimtanel.oref.dto.AlertDto;

import java.util.List;

/*******************************************************************************
 *  Created by Vadim Tanel on 08/01/2020 8:34.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

public interface AlertDataFetcher {
    List<AlertDto> getHistory(String fromDate);

    List<AlertDto> fetchNewAlerts();

    List<AlertDto> getHistory(String fromDate, String toDate);

    List<AlertDto> getLiveAlerts();
}
