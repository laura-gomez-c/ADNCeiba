package com.ceiba.adnparquedero.domain.repository;

import com.ceiba.adnparquedero.domain.model.CarVehicleDomainModel;
import com.ceiba.adnparquedero.domain.model.MotoVehicleDomainModel;
import com.ceiba.adnparquedero.domain.model.ParkingPriceDomainModel;

import java.util.List;

public interface VehicleRepository {

    void createParkingPriceTable(List<ParkingPriceDomainModel> priceDomainModels);

    void registerCar(CarVehicleDomainModel carVehicleDomainModel);

    void registerMoto(MotoVehicleDomainModel motoVehicleDomainModel);

    Integer getCarVehicleTypeTotalOccupancy();

    Integer getMotoVehicleTypeTotalOccupancy();
}
