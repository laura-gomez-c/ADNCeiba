package com.ceiba.adnparquedero.domain.builder;

import com.ceiba.adnparquedero.domain.model.CarVehicleDomainModel;

public class CarVehicleDomainModelBuilder {

    private String licensePlate;

    private String arrivingTime;

    private String leavingTime;

    public CarVehicleDomainModelBuilder() {
        this.licensePlate = "AAA123";
        this.arrivingTime = "2020-10-09T10:10:00-0050";
        this.leavingTime = "2020-10-09T10:10:00-0050";
    }

    public CarVehicleDomainModel build() {
        return new CarVehicleDomainModel(licensePlate, arrivingTime);
    }

    public CarVehicleDomainModelBuilder withArrivingTime(String arrivingTime){
        this.arrivingTime = arrivingTime;
        return this;
    }

    public CarVehicleDomainModelBuilder withLeavingTime(String leavingTime) {
        this.leavingTime = leavingTime;
        return this;
    }
}
