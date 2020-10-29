package com.ceiba.adnparquedero.data.anticorruption;

import com.ceiba.adnparquedero.data.local.model.CarEntity;
import com.ceiba.adnparquedero.domain.model.Car;

public class CarTranslator {

    public Car mapFromCarEntityToCar(CarEntity carEntity) {
        return new Car(carEntity.getVehicleEntity().getLicensePlate(), carEntity.getVehicleEntity().getArrivingTime());
    }
}
