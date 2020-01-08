package com.vadimtanel.oref.service;

import com.vadimtanel.oref.repository.Alert;
import com.vadimtanel.oref.repository.AlertRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/*******************************************************************************
 *  Created by Vadim Tanel on 07/01/2020 8:58.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Service
public class AlertServiceImpl implements AlertService {
    private AlertRepository repository;

    public AlertServiceImpl(AlertRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveAlert(Alert alert) {
        repository.save(alert);
    }

    @Override
    public Alert getAlertById(long id) {
        return repository.findById(id);
    }

    @Override
    public List<Alert> getAlertFromTimeStamp(long timeStamp) {
        return repository.findByTimeStamp(timeStamp);
    }

    @Override
    public List<Alert> getAlertByLocationFromTimeStamp(String location, long timeStamp) {
        return repository.findByLocationAndTimeStamp(location, timeStamp);
    }
}
