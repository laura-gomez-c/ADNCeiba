package com.ceiba.adnparquedero.presentation.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ceiba.adnparquedero.domain.common.util.CalendarOperatorUtil;
import com.ceiba.adnparquedero.domain.model.Moto;
import com.ceiba.adnparquedero.domain.usecase.VehicleUseCase;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

public class MotoViewModel extends ViewModel {

    private final VehicleUseCase vehicleUseCase;

    public MutableLiveData<List<Moto>> listMutableLiveData;

    public MutableLiveData<Boolean> hasValidEntryMutableLiveData;

    public MutableLiveData<Boolean> hasCapacityMutableLiveData;


    @Inject
    public MotoViewModel(VehicleUseCase vehicleUseCase) {
        this.vehicleUseCase = vehicleUseCase;
        this.listMutableLiveData = new MutableLiveData<>(vehicleUseCase.getMotoList());
        this.hasValidEntryMutableLiveData = new MutableLiveData<>();
        this.hasCapacityMutableLiveData = new MutableLiveData<>();
    }

    public void registerMoto(String licensePlate, Float cylinderCapacity) {
        Moto moto = new Moto(licensePlate.toUpperCase(), cylinderCapacity);
        moto.setCalendarArrivingTime(Calendar.getInstance());

        if (moto.hasValidEntryByDay()) {
            vehicleUseCase.registerMoto(moto);
        } else {
            hasValidEntryMutableLiveData.setValue(false);
        }
    }

    public boolean hasMotoCapacity() {
        hasCapacityMutableLiveData.setValue(vehicleUseCase.hasMotoCapacity());
        return hasCapacityMutableLiveData.getValue();
    }

    public void updateMotoList() {
        listMutableLiveData.setValue(vehicleUseCase.getMotoList());
    }
}
