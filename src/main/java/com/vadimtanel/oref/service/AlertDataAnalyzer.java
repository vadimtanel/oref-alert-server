package com.vadimtanel.oref.service;

import com.vadimtanel.oref.dto.AlertDto;

import java.util.List;

/*******************************************************************************
 *  Created by Vadim Tanel on 10/01/2020 21:40.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

public interface AlertDataAnalyzer {
    List<AlertDto> analyzeLiveAlerts(String data);

    List<AlertDto> filterNewAlerts(List<AlertDto> alerts);

    List<AlertDto> analyzeHistoryData(String historyData);

}
