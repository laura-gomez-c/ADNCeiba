package com.ceiba.adnparquedero.domain.repository;

import com.ceiba.adnparquedero.domain.model.Car;
import com.ceiba.adnparquedero.domain.model.Moto;
import com.ceiba.adnparquedero.domain.model.ParkingPrice;
import com.ceiba.adnparquedero.domain.model.Vehicle;

import java.util.List;

public interface VehicleRepository {

    void createParkingPriceTable(List<ParkingPrice> priceDomainModels);

    boolean registerCar(Car car);

    boolean registerMoto(Moto moto);

    Integer getCarVehicleTypeTotalOccupancy();

    Integer getMotoVehicleTypeTotalOccupancy();

    Car getCarVehicleByLicensePlate(String licensePlate);

    Moto getMotoVehicleByLicensePlate(String licensePlate);

    Float getParkingPrice(String vehicleType, String parkingTimeMeasure);

    List<Car> getCarList();

    List<Moto> getMotoList();

    void takeOutVehicle(Vehicle vehicle);
}
