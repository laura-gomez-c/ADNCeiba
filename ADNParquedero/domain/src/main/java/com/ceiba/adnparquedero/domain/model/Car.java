package com.ceiba.adnparquedero.domain.model;

import lombok.Data;

@Data
public class Car extends Vehicle {

    //Constants
    public static final int CAPACITY = 20;

    public Car() {
    }

    public Car(String licensePlate) {
        super(licensePlate);
    }

    public Car(String licensePlate, String arrivingTime) {
        super(licensePlate, arrivingTime);
    }
}
