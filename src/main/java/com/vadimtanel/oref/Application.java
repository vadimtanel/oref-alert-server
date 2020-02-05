package com.vadimtanel.oref;

import com.vadimtanel.oref.logger.ILogger;
import com.vadimtanel.oref.service.GeoPositionFetcher;
import com.vadimtanel.oref.service.GeoPositionService;
import com.vadimtanel.oref.temp.A;
import com.vadimtanel.oref.temp.B;
import com.vadimtanel.oref.temp.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*******************************************************************************
 *  Created by Vadim Tanel on 03/01/2020 0:33.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@SpringBootApplication
public class Application implements CommandLineRunner {
@Autowired
GeoPositionService geoPositionService;

    @Autowired
    ILogger logger;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.Info("🤑🤑 Oref - Server 🤑🤑");
        geoPositionService.getGeoPosition("שרשרת");
        A aClass = new A(0,0);
        A bClass = new B(1,1);
        A cClass = new C(2,2, 2);
        aClass.printX();
        aClass.printY();

        bClass.printX();
        bClass.printY();
        ((B) bClass).printAll();
//        bClass.printAll(); // not Allowed

        cClass.printX();
        cClass.printY();
        ((C) cClass).printAll();
        ((C) cClass).printZ();
//        bClass.printAll(); // not Allowed
//        cClass.printZ(); // not Allowed

        B bBClass = (B) bClass;
        bBClass.printX();
        bBClass.printY();
        bBClass.printAll();

        System.out.println("🤑🤑 Oref - Server 🤑🤑");
    }

}