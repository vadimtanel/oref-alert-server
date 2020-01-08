package com.vadimtanel.oref;

import com.vadimtanel.oref.logger.ILogger;
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
    ILogger logger;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        logger.Info("🤑🤑 Oref - Server 🤑🤑");
        System.out.println("🤑🤑 Oref - Server 🤑🤑");
    }

}