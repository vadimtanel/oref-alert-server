package com.vadimtanel.oref.service.geoPosition;

import com.vadimtanel.oref.dto.GeoPositionDto;
import com.vadimtanel.oref.handler.RestHandler;
import com.vadimtanel.oref.logger.ILogger;
import com.vadimtanel.oref.repository.GeoPosition;
import com.vadimtanel.oref.repository.GeoPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*******************************************************************************
 *  Created by Vadim Tanel on 03/02/2020 18:27.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Service
public class GeoPositionServiceImpl implements GeoPositionService {

    @Autowired
    RestHandler restHandler;

    @Autowired
    ILogger logger;

    private GeoPositionRepository repository;
    private GeoPositionFetcher geoPositionFetcher;

    public GeoPositionServiceImpl(GeoPositionRepository repository, GeoPositionFetcher geoPositionFetcher) {
        this.repository = repository;
        this.geoPositionFetcher = geoPositionFetcher;
    }

    @Override
    public GeoPositionDto getGeoPosition(String location) {
        if (location == null || location == "") {
            return null;
        }

        GeoPosition geoPosition = repository.findByLocation(location);
        if (geoPosition != null) {
            return new GeoPositionDto(geoPosition.getLocation(), geoPosition.getLatt(), geoPosition.getLongt());
        }

        GeoPositionDto geoPositionDto = geoPositionFetcher.getGeoPosition(location);
        if (geoPositionDto != null) {
            geoPosition = new GeoPosition(geoPositionDto.getLocation(), geoPositionDto.getLatt(), geoPositionDto.getLongt());
            repository.save(geoPosition);
        }
        return geoPositionDto;
    }
}
