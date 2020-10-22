package com.ceiba.adnparquedero.builder;

import com.ceiba.adnparquedero.domain.model.VehicleDomainModel;

public class VehicleDomainModelBuilder {

    protected String licensePlate;

    protected String arrivingTime;

    protected String leavingTime;


    public VehicleDomainModelBuilder() {
        this.licensePlate = "AAA123";
        this.arrivingTime = "2020-10-22T10:00:00-0500";
        this.leavingTime = "2020-10-22T20:00:00-0500";
    }

    public VehicleDomainModel build() {
        return new VehicleDomainModel(licensePlate, arrivingTime, leavingTime);
    }
}
