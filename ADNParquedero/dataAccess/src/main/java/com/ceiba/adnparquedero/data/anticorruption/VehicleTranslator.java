package com.ceiba.adnparquedero.data.anticorruption;

import com.ceiba.adnparquedero.data.local.model.VehicleEntity;
import com.ceiba.adnparquedero.domain.model.Car;
import com.ceiba.adnparquedero.domain.model.Moto;

public class VehicleTranslator {

    public VehicleEntity mapFromCarToVehicleEntity(Car car) {
        return new VehicleEntity(car.getLicensePlate(), car.getArrivingTime());
    }

    public VehicleEntity mapFromMotoToVehicleEntity(Moto moto) {
        return new VehicleEntity(moto.getLicensePlate(), moto.getArrivingTime());
    }
}
