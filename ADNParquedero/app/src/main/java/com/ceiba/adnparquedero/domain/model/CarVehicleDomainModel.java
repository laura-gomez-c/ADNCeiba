package com.ceiba.adnparquedero.domain.model;

import lombok.Data;

@Data
public class CarVehicleDomainModel extends VehicleDomainModel {

    public CarVehicleDomainModel(String licensePlate, String arrivingTime, String leavingTime) {
        super(licensePlate, arrivingTime, leavingTime);
    }
}
