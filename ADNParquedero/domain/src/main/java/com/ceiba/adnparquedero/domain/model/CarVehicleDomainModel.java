package com.ceiba.adnparquedero.domain.model;

import lombok.Data;

@Data
public class CarVehicleDomainModel extends VehicleDomainModel {

    //Constants
    public static final int capacity = 20;

    public CarVehicleDomainModel() {
    }

    public CarVehicleDomainModel(String licensePlate, String arrivingTime) {
        super(licensePlate, arrivingTime);
    }
}
