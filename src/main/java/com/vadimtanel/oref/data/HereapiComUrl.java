package com.vadimtanel.oref.data;

/*******************************************************************************
 *  Created by Vadim Tanel on 11/02/2020 17:47.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

public class HereapiComUrl {
    public static String DOMAIN = "https://geocoder.ls.hereapi.com/";
    public static String JSON_URL = DOMAIN + "/6.2/geocode.json";
    public static String CITY_TO_LANG_LATT = JSON_URL + "?apiKey=%s&searchtext=%s";
}
