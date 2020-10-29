package com.ceiba.adnparquedero.domain.usecase;

import com.ceiba.adnparquedero.domain.model.Car;
import com.ceiba.adnparquedero.domain.model.Moto;
import com.ceiba.adnparquedero.domain.model.Vehicle;

import java.util.List;

public interface VehicleUseCase {

    boolean registerCar(Car car);

    boolean registerMoto(Moto moto);

    boolean hasCarCapacity();

    boolean hasMotoCapacity();

    String collectCarParking(String licensePlate);

    String collectMotoParking(String licensePlate);

    List<Car> getCarList();

    List<Moto> getMotoList();

    void takeOutVehicle(Vehicle vehicle);
}
