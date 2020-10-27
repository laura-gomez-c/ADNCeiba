package com.ceiba.adnparquedero.domain.model;

import lombok.Data;

@Data
public class MotoVehicleDomainModel extends VehicleDomainModel {

    //Constants
    public static final int capacity = 10;

    //Attributes
    private Float cylinderCapacity;


    public MotoVehicleDomainModel() {
    }

    public MotoVehicleDomainModel(String licensePlate, String arrivingTime, Float cylinderCapacity1) {
        super(licensePlate, arrivingTime);
        this.cylinderCapacity = cylinderCapacity1;
    }
}
