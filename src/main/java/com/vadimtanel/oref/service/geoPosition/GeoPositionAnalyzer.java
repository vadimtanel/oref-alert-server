package com.vadimtanel.oref.service.geoPosition;

import com.vadimtanel.oref.dto.GeoPositionDto;

/*******************************************************************************
 *  Created by Vadim Tanel on 02/02/2020 16:55.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

public interface GeoPositionAnalyzer {
    String getUrl(String location);

    GeoPositionDto analyzeGeoPositionData(String jsonData);
}
