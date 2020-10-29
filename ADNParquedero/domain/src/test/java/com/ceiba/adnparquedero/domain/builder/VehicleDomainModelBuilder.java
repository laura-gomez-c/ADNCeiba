package com.ceiba.adnparquedero.domain.builder;

public abstract class VehicleDomainModelBuilder {

    protected String licensePlate;

    protected String arrivingTime;

    protected String leavingTime;

    public VehicleDomainModelBuilder() {
        this.licensePlate = "AAA123";
        this.arrivingTime = "2020-10-09T10:10:00-0050";
        this.leavingTime = "2020-10-09T10:10:00-0050";
    }
}
