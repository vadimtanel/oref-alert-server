package com.vadimtanel.oref.service;

import com.vadimtanel.oref.repository.Alert;

import java.text.ParseException;
import java.util.List;

/*******************************************************************************
 *  Created by Vadim Tanel on 10/01/2020 21:40.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

public interface DataAnalyzer {
    List<Alert> analyzeLiveAlerts(String data);

    List<Alert> filterNewAlerts(List<Alert> alerts);

    List<Alert> analyzeHistoryData(String historyData);

}
