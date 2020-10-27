package com.ceiba.adnparquedero.data.model;

import com.ceiba.adnparquedero.domain.model.ParkingPriceDomainModel;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import lombok.Data;

@Data
public class ParkingPriceEntity extends RealmObject {

    @PrimaryKey
    private String id = UUID.randomUUID().toString();

    @Required
    private Float price;

    @Required
    private String vehicleType;

    @Required
    private String parkingTimeMeasure;

    public ParkingPriceEntity() {
    }

    public ParkingPriceEntity(Float price, String vehicleType, String parkingTimeMeasure) {
        this.price = price;
        this.vehicleType = vehicleType;
        this.parkingTimeMeasure = parkingTimeMeasure;
    }

    public void mapFromParkingPriceDomainModel(ParkingPriceDomainModel domainModel) {
        this.setPrice(domainModel.getPrice());
        this.setVehicleType(domainModel.getVehicleType());
        this.setParkingTimeMeasure(domainModel.getParkingTimeMeasure());
    }
}
