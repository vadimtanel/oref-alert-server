package com.vadimtanel.oref.service.geoPosition;

import com.vadimtanel.oref.dto.GeoPositionDto;

/*******************************************************************************
 *  Created by Vadim Tanel on 03/02/2020 18:26.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

public interface GeoPositionService {
    GeoPositionDto getGeoPosition(String location);
}
