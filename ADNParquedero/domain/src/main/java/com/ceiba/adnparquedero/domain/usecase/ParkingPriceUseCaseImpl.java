package com.ceiba.adnparquedero.domain.usecase;

import com.ceiba.adnparquedero.domain.model.ParkingPriceDomainModel;
import com.ceiba.adnparquedero.domain.repository.VehicleRepository;

import java.util.List;

import javax.inject.Inject;

public class ParkingPriceUseCaseImpl implements ParkingPriceUseCase {

    VehicleRepository vehicleRepository;

    @Inject
    public ParkingPriceUseCaseImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void createPriceTable(List<ParkingPriceDomainModel> priceDomainModels) {
        vehicleRepository.createParkingPriceTable(priceDomainModels);
    }
}
