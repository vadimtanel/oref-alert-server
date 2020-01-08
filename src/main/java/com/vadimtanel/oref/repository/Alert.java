package com.vadimtanel.oref.repository;

import javax.persistence.*;

/*******************************************************************************
 *  Created by Vadim Tanel on 06/01/2020 18:29.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Entity
public class Alert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private long timeStamp;
    private String location;
    private String date;
    private String time;

    public Alert() {
        super();
    }

    public Alert(long timeStamp, String location, String date, String time) {
        this.timeStamp = timeStamp;
        this.location = location;
        this.date = date;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
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
}
