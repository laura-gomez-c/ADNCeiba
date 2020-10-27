package com.ceiba.adnparquedero.domain.model;

import lombok.Data;

@Data
public class CarVehicleDomainModel extends VehicleDomainModel {

    public CarVehicleDomainModel() {
    }

    public CarVehicleDomainModel(String licensePlate, String arrivingTime) {
        super(licensePlate, arrivingTime);
    }
}
