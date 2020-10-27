package com.ceiba.adnparquedero.domain.usecase;

import com.ceiba.adnparquedero.domain.model.ParkingPriceDomainModel;

import java.util.List;

public interface ParkingPriceUseCase {

    void createPriceTable(List<ParkingPriceDomainModel> priceDomainModels);
}
