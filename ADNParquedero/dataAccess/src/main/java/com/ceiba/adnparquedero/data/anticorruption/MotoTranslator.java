package com.ceiba.adnparquedero.data.anticorruption;

import com.ceiba.adnparquedero.data.local.model.MotoEntity;
import com.ceiba.adnparquedero.data.local.model.VehicleEntity;
import com.ceiba.adnparquedero.domain.model.Moto;

public class MotoTranslator {

    public Moto mapFromMotoEntityToMoto(MotoEntity motoEntity) {
        return new Moto(motoEntity.getVehicleEntity().getLicensePlate(), motoEntity.getVehicleEntity().getArrivingTime(), motoEntity.getCylinderCapacity());
    }

    public MotoEntity mapFromMotoToMotoEntity(Moto moto, VehicleEntity vehicleEntity) {
        return new MotoEntity(moto.getCylinderCapacity(), vehicleEntity);
    }
}
