package com.ceiba.adnparquedero.builder;

import com.ceiba.adnparquedero.domain.model.CarVehicleDomainModel;
import com.ceiba.adnparquedero.domain.model.VehicleDomainModel;

public class CarVehicleDomainModelBuilder extends VehicleDomainModelBuilder {

    public CarVehicleDomainModel build() {
        return new CarVehicleDomainModel(licensePlate, arrivingTime, leavingTime);
    }
}
