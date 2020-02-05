package com.vadimtanel.oref.service;

import com.vadimtanel.oref.dto.AlertDto;
import com.vadimtanel.oref.repository.Alert;

import java.util.List;

/*******************************************************************************
 *  Created by Vadim Tanel on 07/01/2020 8:58.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

public interface AlertService {
    void saveAlert(Alert alert);

    void saveAlerts(List<AlertDto> alerts);

    Alert getAlertById(long id);

    List<Alert> getAlertFromTimeStamp(long timeStamp);

    List<Alert> getAlertByLocationFromTimeStamp(String location, long timeStamp);
}
