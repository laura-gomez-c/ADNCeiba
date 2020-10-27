package com.ceiba.adnparquedero.data.model;

import com.ceiba.adnparquedero.domain.model.MotoVehicleDomainModel;

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
    Float cylinderCapacity;

    VehicleEntity vehicleEntity;

    public MotoVehicleEntity() {
    }

    public MotoVehicleEntity(Float cylinderCapacity, VehicleEntity vehicleEntity) {
        this.cylinderCapacity = cylinderCapacity;
        this.vehicleEntity = vehicleEntity;
    }

    public void mapFromMotoVehicleDomainModel(MotoVehicleDomainModel motoVehicleDomainModel, VehicleEntity vehicleEntity) {
        this.setCylinderCapacity(motoVehicleDomainModel.getCylinderCapacity());
        this.setVehicleEntity(vehicleEntity);
    }

    public void mapToMotoVehicleDomainModel(MotoVehicleDomainModel motoVehicleDomainModel) {
        motoVehicleDomainModel.setCylinderCapacity(motoVehicleDomainModel.getCylinderCapacity());
        motoVehicleDomainModel.setLicensePlate(this.getVehicleEntity().getLicensePlate());
        motoVehicleDomainModel.setArrivingTime(this.getVehicleEntity().getArrivingTime());
        motoVehicleDomainModel.setLeavingTime(this.getVehicleEntity().getLeavingTime());
    }
}
