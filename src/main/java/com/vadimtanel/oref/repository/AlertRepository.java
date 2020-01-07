package com.vadimtanel.oref.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*******************************************************************************
 *  Created by Vadim Tanel on 06/01/2020 18:29.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Repository
public interface AlertRepository extends CrudRepository<Alert, Long> {
    Alert findById(long id);

    @Query("select a from Alert a where a.timeStamp > %?1")
    List<Alert> findByTimeStamp(long timeStamp);

    @Query("select a from Alert a where a.location like %?1 and a.timeStamp > %?2")
    List<Alert> findByLocationAndTimeStamp(String location, long timeStamp);

}

