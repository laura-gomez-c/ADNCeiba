package com.ceiba.adnparquedero.domain.model;

import lombok.Data;

@Data
public class MotoVehicleDomainModel extends VehicleDomainModel {

    private Float cylinderCapacity;


    public MotoVehicleDomainModel() {
    }

    public MotoVehicleDomainModel(String licensePlate, String arrivingTime, String leavingTime, Float cylinderCapacity1) {
        super(licensePlate, arrivingTime, leavingTime);
        this.cylinderCapacity = cylinderCapacity1;
    }
}
