package com.ceiba.adnparquedero.presentation.viewmodel;

import androidx.lifecycle.ViewModel;

import com.ceiba.adnparquedero.domain.model.ParkingPrice;
import com.ceiba.adnparquedero.domain.usecase.ParkingPriceUseCase;

import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

    private ParkingPriceUseCase parkingPriceUseCase;

    @Inject
    public MainViewModel(ParkingPriceUseCase parkingPriceUseCase) {
        this.parkingPriceUseCase = parkingPriceUseCase;
    }

    public void createPriceTable(List<ParkingPrice> parkingPriceList) {
        parkingPriceUseCase.createPriceTable(parkingPriceList);
    }
}
