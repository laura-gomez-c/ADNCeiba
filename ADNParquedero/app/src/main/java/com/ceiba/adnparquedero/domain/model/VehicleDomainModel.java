package com.ceiba.adnparquedero.domain.model;

import lombok.Data;

@Data
public class VehicleDomainModel {

    private String licensePlate;

    private String arrivingTime;

    private String leavingTime;


    public VehicleDomainModel() {
    }

    public VehicleDomainModel(String licensePlate, String arrivingTime, String leavingTime) {
        this.licensePlate = licensePlate;
        this.arrivingTime = arrivingTime;
        this.leavingTime = leavingTime;
    }
}
