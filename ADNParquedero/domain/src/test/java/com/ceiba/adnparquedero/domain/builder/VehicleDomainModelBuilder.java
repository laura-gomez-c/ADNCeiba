package com.ceiba.adnparquedero.domain.builder;

import com.ceiba.adnparquedero.domain.model.VehicleDomainModel;

public class VehicleDomainModelBuilder {

    private String licensePlate;

    private String arrivingTime;

    private String leavingTime;

    public VehicleDomainModelBuilder() {
        this.licensePlate = "AAA123";
        this.arrivingTime = "2020-10-09T10:10:00-0050";
        this.leavingTime = "2020-10-09T10:10:00-0050";
    }

    public VehicleDomainModel build() {
        return new VehicleDomainModel(licensePlate, arrivingTime);
    }

    public VehicleDomainModelBuilder withLeavingTime(String leavingTime){
        this.leavingTime = leavingTime;
        return this;
    }
}
