package com.ceiba.adnparquedero.data.model;

import com.ceiba.adnparquedero.domain.model.VehicleDomainModel;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import lombok.Data;

@Data
public class VehicleEntity extends RealmObject {

    @PrimaryKey
    private String id = UUID.randomUUID().toString();

    @Required
    private String licensePlate;

    @Required
    private String arrivingTime;

    private String leavingTime;

    public VehicleEntity() {
    }

    public VehicleEntity(String licensePlate, String arrivingTime, String leavingTime) {
        this.licensePlate = licensePlate;
        this.arrivingTime = arrivingTime;
        this.leavingTime = leavingTime;
    }

    public void mapFromVehicleDomainModel(VehicleDomainModel vehicleDomainModel) {
        this.setLicensePlate(vehicleDomainModel.getLicensePlate());
        this.setArrivingTime(vehicleDomainModel.getArrivingTime());
        this.setLeavingTime(vehicleDomainModel.getLeavingTime());
    }
}
