package com.ceiba.adnparquedero.data.local.model;

import com.ceiba.adnparquedero.domain.model.MotoVehicleDomainModel;
import com.ceiba.adnparquedero.domain.model.VehicleDomainModel;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import lombok.Data;

@Data
public class MotoVehicleEntity extends RealmObject {

    @PrimaryKey
    String id = UUID.randomUUID().toString();

    @Required
    String cylinderCapacity;

    VehicleEntity vehicleEntity;

    public MotoVehicleEntity() {
    }

    public MotoVehicleEntity(String cylinderCapacity, VehicleEntity vehicleEntity) {
        this.cylinderCapacity = cylinderCapacity;
        this.vehicleEntity = vehicleEntity;
    }

    public void mapFromMotoVehicleDomainModel(MotoVehicleDomainModel motoVehicleDomainModel, VehicleEntity vehicleEntity) {
        this.setCylinderCapacity(motoVehicleDomainModel.getCylinderCapacity());
        this.setVehicleEntity(vehicleEntity);
    }
}
