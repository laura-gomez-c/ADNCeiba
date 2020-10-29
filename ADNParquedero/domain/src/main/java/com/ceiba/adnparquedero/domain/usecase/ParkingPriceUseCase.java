package com.ceiba.adnparquedero.domain.usecase;

import com.ceiba.adnparquedero.domain.model.ParkingPrice;

import java.util.List;

public interface ParkingPriceUseCase {

    void createPriceTable(List<ParkingPrice> priceDomainModels);
}
