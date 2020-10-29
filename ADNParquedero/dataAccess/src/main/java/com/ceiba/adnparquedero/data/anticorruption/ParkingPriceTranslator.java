package com.ceiba.adnparquedero.data.anticorruption;

import com.ceiba.adnparquedero.data.local.model.ParkingPriceEntity;
import com.ceiba.adnparquedero.domain.model.ParkingPrice;

public class ParkingPriceTranslator {

    public ParkingPriceEntity mapFromParkingPriceToParkingPriceEntity(ParkingPrice parkingPrice) {
        return new ParkingPriceEntity(parkingPrice.getPrice(), parkingPrice.getVehicleType(), parkingPrice.getParkingTimeMeasure());
    }

}
