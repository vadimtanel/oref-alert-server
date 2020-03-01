package com.vadimtanel.oref.repository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/*******************************************************************************
 *  Created by Vadim Tanel on 02/02/2020 18:58.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

@Entity
public class GeoPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String location;
    private double latt;
    private double longt;

    public GeoPosition() {
    }

    public GeoPosition(Long id) {
        this.id = id;
    }

    public GeoPosition(String location, double latt, double longt) {
        this.id = id;
        this.location = location;
        this.latt = latt;
        this.longt = longt;
    }

    public GeoPosition(Long id, String location, double latt, double longt) {
        this.id = id;
        this.location = location;
        this.latt = latt;
        this.longt = longt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
        return "geoPosition{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", latt=" + latt +
                ", longt=" + longt +
                '}';
    }
}
