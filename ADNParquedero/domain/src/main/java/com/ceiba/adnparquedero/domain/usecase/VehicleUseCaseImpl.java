package com.ceiba.adnparquedero.domain.usecase;

import com.ceiba.adnparquedero.domain.common.constant.DateFormat;
import com.ceiba.adnparquedero.domain.common.constant.ParkingTimeMeasure;
import com.ceiba.adnparquedero.domain.common.constant.Regex;
import com.ceiba.adnparquedero.domain.common.constant.VehicleCapacity;
import com.ceiba.adnparquedero.domain.common.constant.VehicleType;
import com.ceiba.adnparquedero.domain.common.util.CalendarOperatorUtil;
import com.ceiba.adnparquedero.domain.model.CarVehicleDomainModel;
import com.ceiba.adnparquedero.domain.model.MotoVehicleDomainModel;
import com.ceiba.adnparquedero.domain.model.VehicleDomainModel;
import com.ceiba.adnparquedero.domain.repository.VehicleRepository;

import java.util.Calendar;

import javax.inject.Inject;

public class VehicleUseCaseImpl implements VehicleUseCase {

    VehicleRepository vehicleRepository;

    @Inject
    public VehicleUseCaseImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    //region Register
    @Override
    public void registerCar(CarVehicleDomainModel carVehicleDomainModel) {
        vehicleRepository.registerCar(carVehicleDomainModel);
    }

    @Override
    public void registerMoto(MotoVehicleDomainModel motoVehicleDomainModel) {
        vehicleRepository.registerMoto(motoVehicleDomainModel);
    }

    @Override
    public String getArrivingTime(Calendar calendar) {
        return CalendarOperatorUtil.parseCalendarToString(calendar, DateFormat.DATE_TIME);
    }
    //endregion

    //region Validations
    @Override
    public boolean hasCarCapacity() {
        return VehicleCapacity.CAR_CAPACITY < vehicleRepository.getCarVehicleTypeTotalOccupancy();
    }

    @Override
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
    @Override
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
    //endregion


    //region Collect parking price
    @Override
    public String collectCarParking(String licensePlate) {
        CarVehicleDomainModel carVehicleDomainModel = vehicleRepository.getCarVehicleByLicensePlate(licensePlate);
        if (carVehicleDomainModel == null) return null;

        //Calculate parking hours
        float parkingHours = calculateParkingHours(carVehicleDomainModel);

        //Calculate vehicle parking price
        float carParkingPrice = calculateVehicleParkingPrice(VehicleType.CAR, (int) parkingHours, vehicleRepository.getParkingPrice(VehicleType.CAR, ParkingTimeMeasure.HOUR), vehicleRepository.getParkingPrice(VehicleType.CAR, ParkingTimeMeasure.DAY));
        return String.valueOf((int) carParkingPrice);
    }

    @Override
    public String collectMotoParking(String licensePlate) {
        MotoVehicleDomainModel motoVehicleDomainModel = vehicleRepository.getMotoVehicleByLicensePlate(licensePlate);
        if (motoVehicleDomainModel == null) return null;

        //Calculate parking hours
        float parkingHours = calculateParkingHours(motoVehicleDomainModel);

        //Calculate vehicle parking price
        float motoParkingPrice = calculateVehicleParkingPrice(VehicleType.MOTO, (int) parkingHours, vehicleRepository.getParkingPrice(VehicleType.MOTO, ParkingTimeMeasure.HOUR), vehicleRepository.getParkingPrice(VehicleType.MOTO, ParkingTimeMeasure.DAY));

        //Validate moto cylinder capacity
        if (motoVehicleDomainModel.getCylinderCapacity() > 500) motoParkingPrice += 2000;
        return String.valueOf((int) motoParkingPrice);
    }

    public float calculateParkingHours(VehicleDomainModel vehicleDomainModel) {
        Calendar arrivingTime = CalendarOperatorUtil.parseStringToCalendar(vehicleDomainModel.getArrivingTime(), DateFormat.DATE_TIME);
        return CalendarOperatorUtil.obtainHourDifference(arrivingTime, Calendar.getInstance());
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
