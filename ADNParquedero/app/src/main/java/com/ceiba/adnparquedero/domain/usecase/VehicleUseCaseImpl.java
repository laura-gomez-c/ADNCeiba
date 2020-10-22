package com.ceiba.adnparquedero.domain.usecase;

import com.ceiba.adnparquedero.base.di.DaggerAppComponent;
import com.ceiba.adnparquedero.domain.common.constant.DateFormat;
import com.ceiba.adnparquedero.domain.common.constant.Regex;
import com.ceiba.adnparquedero.domain.common.constant.VehicleCapacity;
import com.ceiba.adnparquedero.domain.common.util.CalendarUtil;
import com.ceiba.adnparquedero.domain.model.CarVehicleDomainModel;
import com.ceiba.adnparquedero.domain.model.MotoVehicleDomainModel;
import com.ceiba.adnparquedero.domain.model.VehicleDomainModel;
import com.ceiba.adnparquedero.domain.repository.VehicleRepository;

import java.util.Calendar;

import javax.inject.Inject;

public class VehicleUseCaseImpl {

    @Inject
    VehicleRepository vehicleRepository;

    public VehicleUseCaseImpl() {
        DaggerAppComponent.builder().build().inject(this);
    }

    public void registerCar(CarVehicleDomainModel carVehicleDomainModel) {
        vehicleRepository.registerCar(carVehicleDomainModel);
    }

    public void registerMoto(MotoVehicleDomainModel motoVehicleDomainModel) {
        vehicleRepository.registerMoto(motoVehicleDomainModel);
    }

    public String getArrivingTime(Calendar calendar) {
        return CalendarUtil.parseCalendarToString(calendar, DateFormat.DATE_TIME);
    }

    public boolean hasCarCapacity() {
        return VehicleCapacity.CAR_CAPACITY < vehicleRepository.getCarVehicleTypeTotalOccupancy();
    }

    public boolean hasMotoCapacity() {
        return VehicleCapacity.MOTO_CAPACITY < vehicleRepository.getMotoVehicleTypeTotalOccupancy();
    }

    /**
     * Method to validate the parking entry, if the license plate starting with "A" and the entry calendar day is SUNDAY or MONDAY
     * the vehicle won't be able to get in. Otherwise, the vehicle will so.
     *
     * @param vehicleDomainModel, vehicle to be validated.
     * @return true if the entry is valid. Otherwise, false.
     */
    public boolean hasValidEntryByDay(VehicleDomainModel vehicleDomainModel) {
        if (hasLicensePlateStartedWith("A", vehicleDomainModel.getLicensePlate())) {
            Calendar calendar = Calendar.getInstance();
            return Calendar.SUNDAY != calendar.get(Calendar.DAY_OF_WEEK) && Calendar.MONDAY != calendar.get(Calendar.DAY_OF_WEEK);
        }

        return true;
    }

    private boolean hasLicensePlateStartedWith(String initialLetter, String licensePlate) {
        return licensePlate.toUpperCase().matches(Regex.START_WITH.concat(initialLetter.toUpperCase()));
    }

    public String collectParking(String licensePlate) {

        return "nabaaa";
    }
}
