package com.ceiba.adnparquedero.domain.builder;

import com.ceiba.adnparquedero.domain.model.ParkingPriceDomainModel;

public class ParkingPriceDomainModelBuilder {

    private Float price;

    private String vehicleType;

    private String parkingTimeMeasure;

    public ParkingPriceDomainModelBuilder() {
        this.price = 100f;
        this.vehicleType = ParkingPriceDomainModel.CAR;;
        this.parkingTimeMeasure = ParkingPriceDomainModel.DAY;;
    }

    public ParkingPriceDomainModel build() {
        return new ParkingPriceDomainModel(price, vehicleType, parkingTimeMeasure);
    }
}
