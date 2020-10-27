package com.ceiba.adnparquedero.domain.usecase;

import com.ceiba.adnparquedero.domain.common.constant.DateFormat;
import com.ceiba.adnparquedero.domain.common.util.CalendarOperatorUtil;
import com.ceiba.adnparquedero.domain.model.CarVehicleDomainModel;
import com.ceiba.adnparquedero.domain.model.MotoVehicleDomainModel;
import com.ceiba.adnparquedero.domain.model.ParkingPriceDomainModel;
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
    //endregion

    //region Validations
    @Override
    public boolean hasCarCapacity() {
        return CarVehicleDomainModel.capacity > vehicleRepository.getCarVehicleTypeTotalOccupancy();
    }

    @Override
    public boolean hasMotoCapacity() {
        return MotoVehicleDomainModel.capacity > vehicleRepository.getMotoVehicleTypeTotalOccupancy();
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
        float carParkingPrice = calculateVehicleParkingPrice(ParkingPriceDomainModel.CAR, (int) parkingHours, vehicleRepository.getParkingPrice(ParkingPriceDomainModel.CAR, ParkingPriceDomainModel.HOUR), vehicleRepository.getParkingPrice(ParkingPriceDomainModel.CAR, ParkingPriceDomainModel.DAY));
        return String.valueOf((int) carParkingPrice);
    }

    @Override
    public String collectMotoParking(String licensePlate) {
        MotoVehicleDomainModel motoVehicleDomainModel = vehicleRepository.getMotoVehicleByLicensePlate(licensePlate);
        if (motoVehicleDomainModel == null) return null;

        //Calculate parking hours
        float parkingHours = calculateParkingHours(motoVehicleDomainModel);

        //Calculate vehicle parking price
        float motoParkingPrice = calculateVehicleParkingPrice(ParkingPriceDomainModel.MOTO, (int) parkingHours, vehicleRepository.getParkingPrice(ParkingPriceDomainModel.MOTO, ParkingPriceDomainModel.HOUR), vehicleRepository.getParkingPrice(ParkingPriceDomainModel.MOTO, ParkingPriceDomainModel.DAY));

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
                if (!ParkingPriceDomainModel.CAR.equals(vehicleType))
                    totalPrice += hoursLeft * dayPrice;
            }

            return totalPrice;
        }
    }
    //endregion
}
