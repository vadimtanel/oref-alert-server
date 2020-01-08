package com.vadimtanel.oref.controller;

import com.vadimtanel.oref.service.DataFetcherImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*******************************************************************************
 *  Created by Vadim Tanel on 03/01/2020 0:50.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@RestController
@RequestMapping(HistoryController.BASE_URL)
@CrossOrigin
public class HistoryController {
    static final String BASE_URL = "api/history";

    @Autowired
    DataFetcherImpl dataFetcher;

    @RequestMapping(value = "/",
            method = RequestMethod.GET,
            produces = "application/json")
    public ResponseEntity<String> history() {
        String jsonData = dataFetcher.getData("https://www.oref.org.il/Shared/Ajax/GetAlarms.aspx?fromDate=01.12.2019");
        ResponseEntity responseEntity = new ResponseEntity<String>(jsonData, HttpStatus.OK);
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
