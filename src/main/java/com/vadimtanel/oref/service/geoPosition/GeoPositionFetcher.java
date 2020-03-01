package com.vadimtanel.oref.service.geoPosition;

import com.vadimtanel.oref.dto.GeoPositionDto;

/*******************************************************************************
 *  Created by Vadim Tanel on 02/02/2020 9:24.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

public interface GeoPositionFetcher {
    GeoPositionDto getGeoPosition(String cityName);
}
