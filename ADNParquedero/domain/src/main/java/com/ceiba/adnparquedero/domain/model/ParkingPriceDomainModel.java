package com.ceiba.adnparquedero.domain.model;

import lombok.Data;

@Data
public class ParkingPriceDomainModel {

    private Float price;

    private String vehicleType;

    private String parkingTimeMeasure;

    public ParkingPriceDomainModel(Float price, String vehicleType, String parkingTimeMeasure) {
        this.price = price;
        this.vehicleType = vehicleType;
        this.parkingTimeMeasure = parkingTimeMeasure;
    }
}
