package com.vadimtanel.oref.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/*******************************************************************************
 *  Created by Vadim Tanel on 12/01/2020 14:49.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

public class AlertDto {
    @JsonProperty("alertDate")
    private String alertDate;

    @JsonProperty("title")
    private String title;

    @JsonProperty("data")
    private String location;

    public AlertDto() {
    }

    public String getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(String alertDate) {
        this.alertDate = alertDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
