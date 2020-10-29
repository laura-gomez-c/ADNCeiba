package com.ceiba.adnparquedero.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ceiba.adnparquedero.domain.model.Car;
import com.ceiba.adnparquedero.domain.usecase.VehicleUseCase;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

public class CarViewModel extends ViewModel {

    private final VehicleUseCase vehicleUseCase;

    public MutableLiveData<List<Car>> listMutableLiveData;

    public MutableLiveData<Boolean> hasValidEntryMutableLiveData;

    public MutableLiveData<Boolean> hasCapacityMutableLiveData;

    @Inject
    public CarViewModel(VehicleUseCase vehicleUseCase) {
        this.vehicleUseCase = vehicleUseCase;
        this.listMutableLiveData = new MutableLiveData<>(vehicleUseCase.getCarList());
        this.hasValidEntryMutableLiveData = new MutableLiveData<>();
        this.hasCapacityMutableLiveData = new MutableLiveData<>();
    }

    public void registerCar(String licensePlate) {
        Car car = new Car(licensePlate.toUpperCase());
        car.setCalendarArrivingTime(Calendar.getInstance());

        if (car.hasValidEntryByDay()) {
            vehicleUseCase.registerCar(car);
        } else {
            hasValidEntryMutableLiveData.setValue(false);
        }
    }

    public boolean hasMotoCapacity() {
        hasCapacityMutableLiveData.setValue(vehicleUseCase.hasMotoCapacity());
        return hasCapacityMutableLiveData.getValue();
    }

    public void updateCarList() {
        listMutableLiveData.setValue(vehicleUseCase.getCarList());
    }
}
