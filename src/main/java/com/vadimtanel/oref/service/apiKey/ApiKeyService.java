package com.vadimtanel.oref.service.apiKey;

/*******************************************************************************
 *  Created by Vadim Tanel on 27/02/2020 23:33.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

public interface ApiKeyService {
    String getApiKey(String name);

    void save(String apiKey, String name, String site, String user, String password);
}
