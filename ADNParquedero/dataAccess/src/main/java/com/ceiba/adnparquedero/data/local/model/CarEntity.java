package com.ceiba.adnparquedero.data.local.model;

import com.ceiba.adnparquedero.domain.model.Moto;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CarEntity extends RealmObject {

    @PrimaryKey
    String id = UUID.randomUUID().toString();

    VehicleEntity vehicleEntity;

    public CarEntity() {
    }

    public CarEntity(VehicleEntity vehicleEntity) {
        this.vehicleEntity = vehicleEntity;
    }

    public void mapFromMotoVehicleDomainModel(Moto moto, VehicleEntity vehicleEntity) {
        this.setVehicleEntity(vehicleEntity);
    }
}
