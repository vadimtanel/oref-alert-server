package com.vadimtanel.oref.service.apiKey;

import com.vadimtanel.oref.repository.ApiKey;
import com.vadimtanel.oref.repository.ApiKeyRepository;

/*******************************************************************************
 *  Created by Vadim Tanel on 27/02/2020 23:33.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

public class ApiKeyServiceImpl implements ApiKeyService {

    private ApiKeyRepository apiKeyRepository;

    public ApiKeyServiceImpl(ApiKeyRepository apiKeyRepository) {
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public String getApiKey(String name) {
        ApiKey apiKey = apiKeyRepository.findByName(name);
        if (apiKey == null) {
            return null;
        }
        return apiKey.getKey();
    }

    @Override
    public void save(String apiKey, String name, String site, String user, String password) {
        ApiKey apiKeyObj = new ApiKey(apiKey, name, site, user, password);
        apiKeyRepository.save(apiKeyObj);
    }


}
