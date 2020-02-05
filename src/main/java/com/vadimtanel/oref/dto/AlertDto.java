package com.vadimtanel.oref.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vadimtanel.oref.repository.GeoPosition;

/*******************************************************************************
 *  Created by Vadim Tanel on 12/01/2020 14:49.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

public class AlertDto {
    private long timeStamp;
    private String title;
    private String location;
    private String date;
    private String time;
    private GeoPositionDto geoPosition;

    public AlertDto() {
    }

    public AlertDto(long timeStamp, String title, String location, String date, String time) {
        this.timeStamp = timeStamp;
        this.title = title;
        this.location = location;
        this.date = date;
        this.time = time;
        this.geoPosition = new GeoPositionDto(this.location);
    }

    public AlertDto(long timeStamp, String title, String location, String date, String time, GeoPositionDto geoPosition) {
        this.timeStamp = timeStamp;
        this.title = title;
        this.location = location;
        this.date = date;
        this.time = time;
        this.geoPosition = geoPosition;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public GeoPositionDto getGeoPosition() {
        return geoPosition;
    }

    public void setGeoPosition(GeoPositionDto geoPosition) {
        this.geoPosition = geoPosition;
    }

    @Override
    public String toString() {
        return "AlertDto{" +
                "timeStamp=" + timeStamp +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", geoPosition=" + geoPosition +
                '}';
    }
}
