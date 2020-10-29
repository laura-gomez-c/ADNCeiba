package com.ceiba.adnparquedero.domain.model;

import lombok.Data;

@Data
public class ParkingPrice {

    //Constants
    public static final String CAR = "Car";
    public static final String MOTO = "Moto";
    public static final String DAY = "Day";
    public static final String HOUR = "Hour";

    //Attributes
    private Float price;
    private String vehicleType;
    private String parkingTimeMeasure;


    public ParkingPrice(Float price, String vehicleType, String parkingTimeMeasure) {
        this.price = price;
        this.vehicleType = vehicleType;
        this.parkingTimeMeasure = parkingTimeMeasure;
    }
}
