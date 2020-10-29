package com.ceiba.adnparquedero.data.anticorruption;

import com.ceiba.adnparquedero.data.local.model.ParkingPriceEntity;
import com.ceiba.adnparquedero.domain.model.ParkingPrice;

public class ParkingPriceTranslator {

    public ParkingPrice mapFromParkingPriceEntity(ParkingPriceEntity parkingPriceEntity) {
        return new ParkingPrice(parkingPriceEntity.getPrice(), parkingPriceEntity.getVehicleType(), parkingPriceEntity.getParkingTimeMeasure());
    }

    public ParkingPriceEntity mapFromParkingPriceToParkingPriceEntity(ParkingPrice parkingPrice) {
        return new ParkingPriceEntity(parkingPrice.getPrice(), parkingPrice.getVehicleType(), parkingPrice.getParkingTimeMeasure());
    }

}
