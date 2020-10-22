package com.ceiba.adnparquedero.domain.common.constant;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
public @interface VehicleCapacity {

    Integer CAR_CAPACITY = 20;

    Integer MOTO_CAPACITY = 10;
}
