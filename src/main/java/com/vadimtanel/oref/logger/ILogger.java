package com.vadimtanel.oref.logger;

/*******************************************************************************
 *  Created by Vadim Tanel on 03/01/2020 0:35.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

public interface ILogger {
    void Fine(String value);
    void Info(String value);
    void Warning(String value);
    void Error(String value);
    void Sever(String value);
    void Log(String value, LogLevel level);
}
