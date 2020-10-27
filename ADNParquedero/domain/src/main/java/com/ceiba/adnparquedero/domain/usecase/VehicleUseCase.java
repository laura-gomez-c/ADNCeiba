package com.ceiba.adnparquedero.domain.usecase;

import com.ceiba.adnparquedero.domain.model.CarVehicleDomainModel;
import com.ceiba.adnparquedero.domain.model.MotoVehicleDomainModel;
import com.ceiba.adnparquedero.domain.model.VehicleDomainModel;

import java.util.Calendar;

public interface VehicleUseCase {

    void registerCar(CarVehicleDomainModel carVehicleDomainModel);

    void registerMoto(MotoVehicleDomainModel motoVehicleDomainModel);

    String getArrivingTime(Calendar calendar);

    boolean hasCarCapacity();

    boolean hasMotoCapacity();

    boolean hasValidEntryByDay(VehicleDomainModel vehicleDomainModel);

    String collectCarParking(String licensePlate);

    String collectMotoParking(String licensePlate);
}
