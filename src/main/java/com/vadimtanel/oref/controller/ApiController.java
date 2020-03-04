package com.vadimtanel.oref.controller;

import com.vadimtanel.oref.dto.AlertDto;
import com.vadimtanel.oref.service.AlertDataFetcherImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*******************************************************************************
 *  Created by Vadim Tanel on 03/01/2020 0:50.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@RestController
@RequestMapping(ApiController.BASE_URL)
@CrossOrigin
public class ApiController {
    static final String BASE_URL = "api";

    @Autowired
    private AlertDataFetcherImpl dataFetcher;

    @RequestMapping(value = "/history",
            method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity<List<AlertDto>> history(@RequestParam(name = "fromDate") String fromDateStr, @RequestParam(name = "toDate", required = false) String toDateStr) {
        List<AlertDto> alerts = dataFetcher.getHistory(fromDateStr, toDateStr);
        HttpStatus status = alerts == null ? HttpStatus.BAD_GATEWAY : HttpStatus.OK;
        ResponseEntity responseEntity = new ResponseEntity<List<AlertDto>>(alerts, status);
        return responseEntity;
    }

    @RequestMapping(value = "/live",
            method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity<List<AlertDto>> history() {
        List<AlertDto> alerts = dataFetcher.getLiveAlerts();
        HttpStatus status = alerts == null ? HttpStatus.BAD_GATEWAY : HttpStatus.OK;
        ResponseEntity responseEntity = new ResponseEntity<List<AlertDto>>(alerts, status);
        return responseEntity;
    }

    //https://www.oref.org.il/WarningMessages/History/AlertsHistory.json
    /*
    [{"alertDate":"2019-12-25 21:02:10","title":"התרעות פיקוד העורף","data":"אשקלון"},{"alertDate":"2019-12-25 21:02:10","title":"התרעות פיקוד העורף","data":"באר גנים"},{"alertDate":"2019-12-25 21:02:10","title":"התרעות פיקוד העורף","data":"ברכיה"},{"alertDate":"2019-12-25 21:02:10","title":"התרעות פיקוד העורף","data":"הודיה"},{"alertDate":"2019-12-25 21:02:10","title":"התרעות פיקוד העורף","data":"ניצנים"},{"alertDate":"2019-12-25 21:02:10","title":"התרעות פיקוד העורף","data":"ניר ישראל"},{"alertDate":"2019-12-25 21:02:00","title":"התרעות פיקוד העורף","data":"אמונים"},{"alertDate":"2019-12-25 21:02:00","title":"התרעות פיקוד העורף","data":"עזריקם"},{"alertDate":"2019-12-25 21:02:00","title":"התרעות פיקוד העורף","data":"שדה עוזיהו"}]
    */
    //First log from 24.07.14
//    let pathl =
//            "https://www.oref.org.il//Shared/Ajax/GetAlarms.aspx?fromDate=01.12.2019%2012:04:04";
//

}
