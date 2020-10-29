package com.ceiba.adnparquedero.data.local.model;

import com.ceiba.adnparquedero.domain.model.Moto;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MotoEntity extends RealmObject {

    @PrimaryKey
    String id = UUID.randomUUID().toString();

    @Required
    Float cylinderCapacity;

    VehicleEntity vehicleEntity;

    public MotoEntity() {
    }

    public MotoEntity(Float cylinderCapacity, VehicleEntity vehicleEntity) {
        this.cylinderCapacity = cylinderCapacity;
        this.vehicleEntity = vehicleEntity;
    }

    public void mapFromMotoVehicleDomainModel(Moto moto, VehicleEntity vehicleEntity) {
        this.setCylinderCapacity(moto.getCylinderCapacity());
        this.setVehicleEntity(vehicleEntity);
    }

    public void mapToMotoVehicleDomainModel(Moto moto) {
        moto.setCylinderCapacity(moto.getCylinderCapacity());
        moto.setLicensePlate(this.getVehicleEntity().getLicensePlate());
        moto.setArrivingTime(this.getVehicleEntity().getArrivingTime());
        moto.setLeavingTime(this.getVehicleEntity().getLeavingTime());
    }
}
