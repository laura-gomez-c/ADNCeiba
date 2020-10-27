package com.ceiba.adnparquedero.data.repository;

import android.util.Log;

import com.ceiba.adnparquedero.data.model.CarVehicleEntity;
import com.ceiba.adnparquedero.data.model.MotoVehicleEntity;
import com.ceiba.adnparquedero.data.model.ParkingPriceEntity;
import com.ceiba.adnparquedero.data.model.VehicleEntity;
import com.ceiba.adnparquedero.domain.model.CarVehicleDomainModel;
import com.ceiba.adnparquedero.domain.model.MotoVehicleDomainModel;
import com.ceiba.adnparquedero.domain.model.ParkingPriceDomainModel;
import com.ceiba.adnparquedero.domain.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

public class VehicleRepositoryImpl implements VehicleRepository {

    @Inject
    public VehicleRepositoryImpl() {

    }

    @Override
    public void createParkingPriceTable(List<ParkingPriceDomainModel> priceDomainModels) {
        try (Realm realm = Realm.getDefaultInstance()) {
            List<ParkingPriceEntity> entities = new ArrayList<>();
            for (ParkingPriceDomainModel priceDomainModel : priceDomainModels) {

                //Map from domain model
                ParkingPriceEntity priceEntity = new ParkingPriceEntity();
                priceEntity.mapFromParkingPriceDomainModel(priceDomainModel);
                entities.add(priceEntity);

                //Insert locally
            }
            realm.executeTransaction(r -> r.insertOrUpdate(entities));
            Log.d("executing..", "hecho");
        }
    }

    @Override
    public Integer getCarVehicleTypeTotalOccupancy() {
        try (Realm realm = Realm.getDefaultInstance()) {
            return realm.where(CarVehicleEntity.class).findAll().size();
        }
    }

    @Override
    public Integer getMotoVehicleTypeTotalOccupancy() {
        try (Realm realm = Realm.getDefaultInstance()) {
            return realm.where(MotoVehicleEntity.class).findAll().size();
        }
    }

    @Override
    public void registerCar(CarVehicleDomainModel carVehicleDomainModel) {
        try (Realm realm = Realm.getDefaultInstance()) {
            //Map super class from domain model
            VehicleEntity vehicleEntity = new VehicleEntity();
            vehicleEntity.mapFromVehicleDomainModel(carVehicleDomainModel);

            //Insert locally
            realm.executeTransaction(r -> r.insertOrUpdate(vehicleEntity));

            //Map child class from domain model
            CarVehicleEntity carVehicleEntity = new CarVehicleEntity();
            carVehicleEntity.mapFromCarVehicleDomainModel(vehicleEntity);

            //Insert locally
            realm.executeTransaction(r -> r.insertOrUpdate(carVehicleEntity));
        }
    }

    @Override
    public void registerMoto(MotoVehicleDomainModel motoVehicleDomainModel) {
        try (Realm realm = Realm.getDefaultInstance()) {

            //Map super class from domain model
            VehicleEntity vehicleEntity = new VehicleEntity();
            vehicleEntity.mapFromVehicleDomainModel(motoVehicleDomainModel);

            //Insert locally
            realm.executeTransaction(r -> r.insertOrUpdate(vehicleEntity));

            //Map child class from domain model
            MotoVehicleEntity motoCehicleEntity = new MotoVehicleEntity();
            motoCehicleEntity.mapFromMotoVehicleDomainModel(motoVehicleDomainModel, vehicleEntity);

            //Insert locally
            realm.executeTransaction(r -> r.insertOrUpdate(motoCehicleEntity));
        }
    }

    @Override
    public CarVehicleDomainModel getCarVehicleByLicensePlate(String licensePlate) {
        try (Realm realm = Realm.getDefaultInstance()) {
            CarVehicleEntity carVehicleEntity = realm.where(CarVehicleEntity.class).equalTo("vehicleEntity.licensePlate", licensePlate).findFirst();
            if (carVehicleEntity == null) return null;

            //Map child class to domain model
            CarVehicleDomainModel carVehicleDomainModel = new CarVehicleDomainModel();
            carVehicleEntity.mapToCarVehicleDomainModel(carVehicleDomainModel);
            return carVehicleDomainModel;
        }
    }

    @Override
    public MotoVehicleDomainModel getMotoVehicleByLicensePlate(String licensePlate) {
        try (Realm realm = Realm.getDefaultInstance()) {
            MotoVehicleEntity motoCehicleEntity = realm.where(MotoVehicleEntity.class).equalTo("vehicleEntity.licensePlate", licensePlate).findFirst();
            if (motoCehicleEntity == null) return null;

            //Map child class to domain model
            MotoVehicleDomainModel motoVehicleDomainModel = new MotoVehicleDomainModel();
            motoCehicleEntity.mapToMotoVehicleDomainModel(motoVehicleDomainModel);
            return motoVehicleDomainModel;
        }
    }

    @Override
    public Float getParkingPrice(String vehicleType, String parkingTimeMeasure) {
        try (Realm realm = Realm.getDefaultInstance()) {
            ParkingPriceEntity priceEntity = realm.where(ParkingPriceEntity.class)
                    .equalTo("vehicleType", vehicleType)
                    .equalTo("parkingTimeMeasure", parkingTimeMeasure)
                    .findFirst();
            if (priceEntity == null) return null;

            return priceEntity.getPrice();
        }
    }
}