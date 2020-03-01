package com.vadimtanel.oref.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*******************************************************************************
 *  Created by Vadim Tanel on 27/02/2020 23:21.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Repository
public interface ApiKeyRepository extends CrudRepository<ApiKey, Long> {
    ApiKey findById(long id);

    ApiKey findByKey(String key);

    ApiKey findByName(String name);
}