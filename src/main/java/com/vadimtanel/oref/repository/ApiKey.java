package com.vadimtanel.oref.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*******************************************************************************
 *  Created by Vadim Tanel on 06/01/2020 18:29.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Entity
public class ApiKey {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String key;
    private String name;
    private String site;
    private String user;
    private String password;

    public ApiKey() {
        super();
    }

    public ApiKey(String key, String name, String site, String user, String password) {
        this.key = key;
        this.name = name;
        this.site = site;
        this.user = user;
        this.password = password;
    }

    public ApiKey(Long id, String key, String name, String site, String user, String password) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.site = site;
        this.user = user;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
