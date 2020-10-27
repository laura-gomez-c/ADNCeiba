package com.ceiba.adnparquedero.presentation.viewmodel;

import androidx.lifecycle.ViewModel;

import com.ceiba.adnparquedero.domain.model.ParkingPriceDomainModel;
import com.ceiba.adnparquedero.domain.usecase.ParkingPriceUseCase;
import com.ceiba.adnparquedero.domain.usecase.ParkingPriceUseCaseImpl;

import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel {

    private ParkingPriceUseCase parkingPriceUseCase;

    @Inject
    public MainViewModel(ParkingPriceUseCase parkingPriceUseCase) {
        this.parkingPriceUseCase = parkingPriceUseCase;
    }

    public void createPriceTable(List<ParkingPriceDomainModel> priceDomainModels) {
        parkingPriceUseCase.createPriceTable(priceDomainModels);
    }

}
