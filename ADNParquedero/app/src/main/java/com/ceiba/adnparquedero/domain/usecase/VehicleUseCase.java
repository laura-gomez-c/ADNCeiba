package com.ceiba.adnparquedero.domain.usecase;

import com.ceiba.adnparquedero.base.di.DaggerAppComponent;
import com.ceiba.adnparquedero.domain.common.constant.DateFormat;
import com.ceiba.adnparquedero.domain.common.constant.ParkingTimeMeasure;
import com.ceiba.adnparquedero.domain.common.constant.Regex;
import com.ceiba.adnparquedero.domain.common.constant.VehicleCapacity;
import com.ceiba.adnparquedero.domain.common.constant.VehicleType;
import com.ceiba.adnparquedero.domain.common.util.CalendarUtil;
import com.ceiba.adnparquedero.domain.model.CarVehicleDomainModel;
import com.ceiba.adnparquedero.domain.model.MotoVehicleDomainModel;
import com.ceiba.adnparquedero.domain.model.VehicleDomainModel;
import com.ceiba.adnparquedero.domain.repository.VehicleRepository;

import java.util.Calendar;

import javax.inject.Inject;

public class VehicleUseCase {

    @Inject
    VehicleRepository vehicleRepository;

    public VehicleUseCase() {
        DaggerAppComponent.builder().build().inject(this);
    }

    //region Register
    public void registerCar(CarVehicleDomainModel carVehicleDomainModel) {
        vehicleRepository.registerCar(carVehicleDomainModel);
    }

    public void registerMoto(MotoVehicleDomainModel motoVehicleDomainModel) {
        vehicleRepository.registerMoto(motoVehicleDomainModel);
    }

    public String getArrivingTime(Calendar calendar) {
        return CalendarUtil.parseCalendarToString(calendar, DateFormat.DATE_TIME);
    }
    //endregion

    //region Validations
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

    public String collectCarParking(String licensePlate) {
        CarVehicleDomainModel carVehicleDomainModel = vehicleRepository.getCarVehicleByLicensePlate(licensePlate);
        if (carVehicleDomainModel != null) return null;

        //Calculate parking hours
        float parkingHours = calculateParkingHours(carVehicleDomainModel);

        //Calculate vehicle parking price
        float carParkingPrice = calculateVehicleParkingPrice(VehicleType.CAR, (int) parkingHours, vehicleRepository.getParkingPrice(VehicleType.CAR, ParkingTimeMeasure.HOUR), vehicleRepository.getParkingPrice(VehicleType.CAR, ParkingTimeMeasure.DAY));
        return String.valueOf((int) carParkingPrice);
    }
    //endregion

    //region Collect parking price
    public String collectMotoParking(String licensePlate) {
        MotoVehicleDomainModel motoVehicleDomainModel = vehicleRepository.getMotoVehicleByLicensePlate(licensePlate);
        if (motoVehicleDomainModel != null) return null;

        //Calculate parking hours
        float parkingHours = calculateParkingHours(motoVehicleDomainModel);

        //Calculate vehicle parking price
        float motoParkingPrice = calculateVehicleParkingPrice(VehicleType.MOTO, (int) parkingHours, vehicleRepository.getParkingPrice(VehicleType.MOTO, ParkingTimeMeasure.HOUR), vehicleRepository.getParkingPrice(VehicleType.MOTO, ParkingTimeMeasure.DAY));

        //Validate moto cylinder capacity
        assert motoVehicleDomainModel.getCylinderCapacity() != null;
        if (motoVehicleDomainModel.getCylinderCapacity() > 500) motoParkingPrice += 2000;
        return String.valueOf((int) motoParkingPrice);
    }

    public float calculateParkingHours(VehicleDomainModel vehicleDomainModel) {
        Calendar arrivingTime = CalendarUtil.parseStringToCalendar(vehicleDomainModel.getArrivingTime(), DateFormat.DATE_TIME);
        return CalendarUtil.obtainHourDifference(arrivingTime, Calendar.getInstance());
    }

    public float calculateVehicleParkingPrice(String vehicleType, Integer parkingHours, Float hourPrice, Float dayPrice) {
        if (parkingHours < 24) {
            return parkingHours * hourPrice;
        } else {
            int days = parkingHours / 24;
            float totalPrice = days * dayPrice;
            int hoursLeft = parkingHours - (days * 24);

            if (hoursLeft >= 9) {
                totalPrice += dayPrice;
            } else {
                if (!VehicleType.CAR.equals(vehicleType))
                    totalPrice += hoursLeft * dayPrice;
            }

            return totalPrice;
        }
    }
    //endregion
}
