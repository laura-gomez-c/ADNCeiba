package com.ceiba.adnparquedero.domain.model;

import lombok.Data;

@Data
public class MotoVehicleDomainModel extends VehicleDomainModel {

    private String cylinderCapacity;

    public MotoVehicleDomainModel(String licensePlate, String arrivingTime, String leavingTime,  String cylinderCapacity1) {
        super(licensePlate, arrivingTime, leavingTime);
        this.cylinderCapacity = cylinderCapacity1;
    }
}
