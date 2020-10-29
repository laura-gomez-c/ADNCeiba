package com.ceiba.adnparquedero.domain.builder;

import com.ceiba.adnparquedero.domain.model.ParkingPrice;

public class ParkingPriceDomainModelBuilder {

    private Float price;

    private String vehicleType;

    private String parkingTimeMeasure;

    public ParkingPriceDomainModelBuilder() {
        this.price = 100f;
        this.vehicleType = ParkingPrice.CAR;;
        this.parkingTimeMeasure = ParkingPrice.DAY;;
    }

    public ParkingPrice build() {
        return new ParkingPrice(price, vehicleType, parkingTimeMeasure);
    }
}
