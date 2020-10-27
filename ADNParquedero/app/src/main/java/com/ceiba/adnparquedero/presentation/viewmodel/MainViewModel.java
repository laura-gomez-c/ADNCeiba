package com.ceiba.adnparquedero.presentation.viewmodel;

import androidx.lifecycle.ViewModel;

import com.ceiba.adnparquedero.domain.model.ParkingPriceDomainModel;
import com.ceiba.adnparquedero.domain.usecase.ParkingPriceUseCase;
import com.ceiba.adnparquedero.domain.usecase.VehicleUseCase;

import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

    private VehicleUseCase vehicleUseCase;

    private ParkingPriceUseCase parkingPriceUseCase;

    @Inject
    public MainViewModel(VehicleUseCase vehicleUseCase, ParkingPriceUseCase parkingPriceUseCase) {
        this.vehicleUseCase = vehicleUseCase;
        this.parkingPriceUseCase = parkingPriceUseCase;
    }

    public void createPriceTable(List<ParkingPriceDomainModel> parkingPriceDomainModelList) {
        parkingPriceUseCase.createPriceTable(parkingPriceDomainModelList);
    }

    public boolean hasCarCapacity() {
        return vehicleUseCase.hasCarCapacity();
    }

    public boolean hasMotoCapacity() {
        return vehicleUseCase.hasMotoCapacity();
    }

}
