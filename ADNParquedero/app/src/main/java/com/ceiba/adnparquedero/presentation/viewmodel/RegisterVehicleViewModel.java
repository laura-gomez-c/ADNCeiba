package com.ceiba.adnparquedero.presentation.viewmodel;

import androidx.lifecycle.ViewModel;

import com.ceiba.adnparquedero.domain.model.ParkingPriceDomainModel;
import com.ceiba.adnparquedero.domain.usecase.ParkingPriceUseCase;
import com.ceiba.adnparquedero.domain.usecase.VehicleUseCase;

import java.util.List;

import javax.inject.Inject;

public class RegisterVehicleViewModel extends ViewModel {

    private VehicleUseCase vehicleUseCase;

    public String vehicleType;

    @Inject
    public RegisterVehicleViewModel(VehicleUseCase vehicleUseCase) {
        this.vehicleUseCase = vehicleUseCase;
    }

    public void registerCar() {

    }
}
