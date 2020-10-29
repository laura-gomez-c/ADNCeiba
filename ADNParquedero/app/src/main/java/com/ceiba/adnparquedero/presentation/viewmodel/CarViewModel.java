package com.ceiba.adnparquedero.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ceiba.adnparquedero.domain.model.Car;
import com.ceiba.adnparquedero.domain.model.Vehicle;
import com.ceiba.adnparquedero.domain.usecase.VehicleUseCase;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

public class CarViewModel extends ViewModel {

    private final VehicleUseCase vehicleUseCase;

    public MutableLiveData<List<Car>> listMutableLiveData;

    public MutableLiveData<Boolean> hasValidEntryMutableLiveData;

    public MutableLiveData<Boolean> hasCapacityMutableLiveData;

    public MutableLiveData<Boolean> carRegisteredMutableLiveData;

    @Inject
    public CarViewModel(VehicleUseCase vehicleUseCase) {
        this.vehicleUseCase = vehicleUseCase;
        this.listMutableLiveData = new MutableLiveData<>(vehicleUseCase.getCarList());
        this.hasValidEntryMutableLiveData = new MutableLiveData<>();
        this.hasCapacityMutableLiveData = new MutableLiveData<>();
        this.carRegisteredMutableLiveData = new MutableLiveData<>();
    }

    public void registerCar(String licensePlate) {
        Car car = new Car(licensePlate.toUpperCase());
        car.setCalendarArrivingTime(Calendar.getInstance());

        if (car.hasValidEntryByDay()) {
            carRegisteredMutableLiveData.setValue(vehicleUseCase.registerCar(car));
        } else {
            hasValidEntryMutableLiveData.setValue(false);
        }
    }

    public boolean hasCarCapacity() {
        hasCapacityMutableLiveData.setValue(vehicleUseCase.hasCarCapacity());
        return hasCapacityMutableLiveData.getValue();
    }

    public void updateCarList() {
        listMutableLiveData.setValue(vehicleUseCase.getCarList());
    }

    public String collectCarParking(Vehicle vehicle) {
        return vehicleUseCase.collectCarParking(vehicle.getLicensePlate());
    }

    public void takeOutVehicle(Vehicle vehicle) {
        vehicle.setCalendarLeavingTime(Calendar.getInstance());
        vehicleUseCase.takeOutVehicle(vehicle);
    }
}
