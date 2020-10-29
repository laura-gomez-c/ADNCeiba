package com.ceiba.adnparquedero.domain.usecase;

import com.ceiba.adnparquedero.domain.model.Car;
import com.ceiba.adnparquedero.domain.model.Moto;

import java.util.List;

public interface VehicleUseCase {

    void registerCar(Car car);

    void registerMoto(Moto moto);

    boolean hasCarCapacity();

    boolean hasMotoCapacity();

    String collectCarParking(String licensePlate);

    String collectMotoParking(String licensePlate);

    List<Car> getCarList();

    List<Moto> getMotoList();
}
