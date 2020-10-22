package com.ceiba.adnparquedero.data.local.model;

import com.ceiba.adnparquedero.domain.model.MotoVehicleDomainModel;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import lombok.Data;

@Data
public class CarVehicleEntity extends RealmObject {

    @PrimaryKey
    String id = UUID.randomUUID().toString();

    @Required
    VehicleEntity vehicleEntity;

    public CarVehicleEntity() {
    }

    public CarVehicleEntity(VehicleEntity vehicleEntity) {
        this.vehicleEntity = vehicleEntity;
    }

    public void mapFromCarVehicleDomainModel(VehicleEntity vehicleEntity) {
        this.setVehicleEntity(vehicleEntity);
    }
}
