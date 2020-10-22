package com.ceiba.adnparquedero.domain.common.constant;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
public @interface VehicleType {

    String CAR = "Car";

    String MOTO = "Moto";
}
