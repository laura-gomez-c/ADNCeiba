package com.ceiba.adnparquedero.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Moto extends Vehicle {

    //Constants
    public static final int CAPACITY = 10;

    //Attributes
    private Float cylinderCapacity;

    public Moto(String licensePlate, Float cylinderCapacity) {
        super(licensePlate);
        this.cylinderCapacity = cylinderCapacity;
    }

    public Moto(String licensePlate, String arrivingTime, Float cylinderCapacity1) {
        super(licensePlate, arrivingTime);
        this.cylinderCapacity = cylinderCapacity1;
    }
}
