package com.vadimtanel.oref.data;

/*******************************************************************************
 *  Created by Vadim Tanel on 08/01/2020 8:43.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

public abstract class OrefUrl {
    public static String DOMAIN = "https://www.oref.org.il/";
    public static String HISTORY = DOMAIN + "Shared/Ajax/GetAlarms.aspx";
    public static String QUERY_PARAM_HISTORY_FROM_DATE = "fromDate";
    public static String QUERY_PARAM_HISTORY_TO_DATE = "toDate";
    public static String LIVE = DOMAIN + "WarningMessages/History/AlertsHistory.json";
    public static String HISTORY_FORM_DATE = String.format("%s?%s=%%s",HISTORY, QUERY_PARAM_HISTORY_FROM_DATE);
    public static String HISTORY_FORM_DATE_TO_DATE = String.format("%s?%s=%%s&%s=%%s",HISTORY, QUERY_PARAM_HISTORY_FROM_DATE, QUERY_PARAM_HISTORY_TO_DATE);


}
