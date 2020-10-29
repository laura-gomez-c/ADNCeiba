package com.ceiba.adnparquedero.data.local.model;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
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
}
