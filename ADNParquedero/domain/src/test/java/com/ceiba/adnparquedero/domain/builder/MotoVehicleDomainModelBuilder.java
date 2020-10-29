package com.ceiba.adnparquedero.domain.builder;

import com.ceiba.adnparquedero.domain.model.Moto;

public class MotoVehicleDomainModelBuilder {

    private String licensePlate;

    private String arrivingTime;

    private String leavingTime;

    private Float cylinderCapacity;

    public MotoVehicleDomainModelBuilder() {
        this.licensePlate = "AAA123";
        this.arrivingTime = "2020-10-09T10:10:00-0050";
        this.leavingTime = "2020-10-09T10:10:00-0050";
        this.cylinderCapacity = 500f;
    }

    public Moto build() {
        return new Moto(licensePlate, arrivingTime, cylinderCapacity);
    }
}
