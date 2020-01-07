package com.vadimtanel.oref.logger;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*******************************************************************************
 *  Created by Vadim Tanel on 03/01/2020 0:35.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Component
public class LoggerImpl implements ILogger {
    @Autowired
    Logger log;

    public LoggerImpl() {

    }

    @Override
    public void Fine(String value) {
        log.info(value);
        System.out.println("Fine: " + value);
    }

    @Override
    public void Info(String value) {
        log.info(value);
        System.out.println("Info: " + value);
    }

    @Override
    public void Warning(String value) {
        log.warn(value);
        System.out.println("Warning: " + value);
    }

    @Override
    public void Error(String value) {
        log.error(value);
        System.out.println("Error: " + value);
    }

    @Override
    public void Sever(String value) {
        log.error(value);
        System.out.println("Sever: " + value);
    }

    @Override
    public void Log(String value, LogLevel level) {
        log.trace(value);
        System.out.println(level + ": " + value);
    }
}
