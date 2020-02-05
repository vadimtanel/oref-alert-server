package com.vadimtanel.oref.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*******************************************************************************
 *  Created by Vadim Tanel on 02/02/2020 18:58.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Repository
public interface GeoPositionRepository extends CrudRepository<GeoPosition, Long> {
    GeoPosition findById(long id);
    GeoPosition findByLocation(String location);
}