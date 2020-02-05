package com.vadimtanel.oref.dto;

/*******************************************************************************
 *  Created by Vadim Tanel on 02/02/2020 13:05.
 *  Copyright © 2020 Vadim Tanel.
 *  All rights reserved.
 ******************************************************************************/

public class GeoPositionDto {
    private String location;
    private double latt;
    private double longt;

    public GeoPositionDto() {
    }

    public GeoPositionDto(String location) {
        this.location = location;
    }

    public GeoPositionDto(String location, double latt, double longt) {
        this.location = location;
        this.latt = latt;
        this.longt = longt;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getLongt() {
        return longt;
    }

    public void setLongt(double longt) {
        this.longt = longt;
    }

    public double getLatt() {
        return latt;
    }

    public void setLatt(double latt) {
        this.latt = latt;
    }

    @Override
    public String toString() {
        return "GeoPositionDto{" +
                "location='" + location + '\'' +
                ", longt=" + longt +
                ", latt=" + latt +
                '}';
    }
}
