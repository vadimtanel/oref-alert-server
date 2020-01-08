package com.vadimtanel.oref.controller;

import com.vadimtanel.oref.service.DataFetcherImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/*******************************************************************************
 *  Created by Vadim Tanel on 05/01/2020 21:36.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@RestController
@RequestMapping(LiveController.BASE_URL)
@CrossOrigin
public class LiveController {
    static final String BASE_URL = "api/live";

    @Autowired
    DataFetcherImpl dataFetcher;

    @RequestMapping(value = "/",
            method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity<String> history() {
        // get data from the db scheduler saved
        String jsonData = dataFetcher.getData("https://www.oref.org.il/Shared/Ajax/GetAlarms.aspx?fromDate=" + getCurrentUTCTime());
        ResponseEntity responseEntity = new ResponseEntity<String>(jsonData, HttpStatus.OK);
        return responseEntity;
    }

    private String getCurrentUTCTime(){
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC+01:59:59"));
        return dateFormat.format(date);
    }
}
