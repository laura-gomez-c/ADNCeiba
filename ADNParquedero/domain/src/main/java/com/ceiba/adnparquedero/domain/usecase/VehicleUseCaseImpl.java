package com.ceiba.adnparquedero.domain.usecase;

import com.ceiba.adnparquedero.domain.common.util.CalendarOperatorUtil;
import com.ceiba.adnparquedero.domain.model.Car;
import com.ceiba.adnparquedero.domain.model.Moto;
import com.ceiba.adnparquedero.domain.model.ParkingPrice;
import com.ceiba.adnparquedero.domain.model.Vehicle;
import com.ceiba.adnparquedero.domain.repository.VehicleRepository;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

public class VehicleUseCaseImpl implements VehicleUseCase {

    VehicleRepository vehicleRepository;

    @Inject
    public VehicleUseCaseImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    //region Register
    @Override
    public void registerCar(Car car) {
        vehicleRepository.registerCar(car);
    }

    @Override
    public void registerMoto(Moto moto) {
        vehicleRepository.registerMoto(moto);
    }
    //endregion

    //region Get

    @Override
    public List<Car> getCarList() {
        return vehicleRepository.getCarList();
    }

    @Override
    public List<Moto> getMotoList() {
        return vehicleRepository.getMotoList();
    }

    //endregion

    //region Validations
    @Override
    public boolean hasCarCapacity() {
        return Car.CAPACITY > vehicleRepository.getCarVehicleTypeTotalOccupancy();
    }

    @Override
    public boolean hasMotoCapacity() {
        return Moto.CAPACITY > vehicleRepository.getMotoVehicleTypeTotalOccupancy();
    }
    //endregion

    //region Collect parking price
    @Override
    public String collectCarParking(String licensePlate) {
        Car car = vehicleRepository.getCarVehicleByLicensePlate(licensePlate);
        if (car == null) return null;

        //Calculate parking hours
        float parkingHours = calculateParkingHours(car);

        //Calculate vehicle parking price
        float carParkingPrice = calculateVehicleParkingPrice(ParkingPrice.CAR, (int) parkingHours, vehicleRepository.getParkingPrice(ParkingPrice.CAR, ParkingPrice.HOUR), vehicleRepository.getParkingPrice(ParkingPrice.CAR, ParkingPrice.DAY));
        return String.valueOf((int) carParkingPrice);
    }

    @Override
    public String collectMotoParking(String licensePlate) {
        Moto moto = vehicleRepository.getMotoVehicleByLicensePlate(licensePlate);
        if (moto == null) return null;

        //Calculate parking hours
        float parkingHours = calculateParkingHours(moto);

        //Calculate vehicle parking price
        float motoParkingPrice = calculateVehicleParkingPrice(ParkingPrice.MOTO, (int) parkingHours, vehicleRepository.getParkingPrice(ParkingPrice.MOTO, ParkingPrice.HOUR), vehicleRepository.getParkingPrice(ParkingPrice.MOTO, ParkingPrice.DAY));

        //Validate moto cylinder capacity
        if (moto.getCylinderCapacity() > 500) motoParkingPrice += 2000;
        return String.valueOf((int) motoParkingPrice);
    }

    public float calculateParkingHours(Vehicle vehicle) {
        Calendar arrivingTime = CalendarOperatorUtil.parseStringToCalendar(vehicle.getArrivingTime(), Vehicle.DATE_TIME);
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
                if (!ParkingPrice.CAR.equals(vehicleType))
                    totalPrice += hoursLeft * dayPrice;
            }

            return totalPrice;
        }
    }
    //endregion
}
