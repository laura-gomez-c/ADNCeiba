package com.ceiba.adnparquedero.data.local.model;

import com.ceiba.adnparquedero.domain.model.CarVehicleDomainModel;
import com.ceiba.adnparquedero.domain.model.MotoVehicleDomainModel;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;

@Data
public class CarVehicleEntity extends RealmObject {

    @PrimaryKey
    String id = UUID.randomUUID().toString();

    VehicleEntity vehicleEntity;

    public CarVehicleEntity() {
    }

    public CarVehicleEntity(VehicleEntity vehicleEntity) {
        this.vehicleEntity = vehicleEntity;
    }

    public void mapFromCarVehicleDomainModel(VehicleEntity vehicleEntity) {
        this.setVehicleEntity(vehicleEntity);
    }

    public void mapToCarVehicleDomainModel(CarVehicleDomainModel carVehicleDomainModel) {
        carVehicleDomainModel.setLicensePlate(this.getVehicleEntity().getLicensePlate());
        carVehicleDomainModel.setArrivingTime(this.getVehicleEntity().getArrivingTime());
        carVehicleDomainModel.setLeavingTime(this.getVehicleEntity().getLeavingTime());
    }
}
