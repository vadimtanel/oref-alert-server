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
    private String title;
    private String location;
    private String date;
    private String time;
    private double latt;
    private double longt;

    public Alert() {
        super();
    }

    public Alert(long timeStamp, String title, String location, String date, String time) {
        this.timeStamp = timeStamp;
        this.title = title;
        this.location = location;
        this.date = date;
        this.time = time;
    }

    public Alert(long timeStamp, String title, String location, String date, String time, double latt, double longt) {
        this.timeStamp = timeStamp;
        this.title = title;
        this.location = location;
        this.date = date;
        this.time = time;
        this.latt = latt;
        this.longt = longt;
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

    public double getLatt() {
        return latt;
    }

    public void setLatt(double latt) {
        this.latt = latt;
    }

    public double getLongt() {
        return longt;
    }

    public void setLongt(double longt) {
        this.longt = longt;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "id=" + id +
                ", timeStamp=" + timeStamp +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", latt=" + latt +
                ", longt=" + longt +
                '}';
    }
}
