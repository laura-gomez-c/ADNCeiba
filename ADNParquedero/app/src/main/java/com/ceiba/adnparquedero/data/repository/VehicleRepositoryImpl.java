package com.ceiba.adnparquedero.data.repository;

import com.ceiba.adnparquedero.data.local.model.CarVehicleEntity;
import com.ceiba.adnparquedero.data.local.model.MotoVehicleEntity;
import com.ceiba.adnparquedero.data.local.model.ParkingPriceEntity;
import com.ceiba.adnparquedero.data.local.model.VehicleEntity;
import com.ceiba.adnparquedero.domain.model.CarVehicleDomainModel;
import com.ceiba.adnparquedero.domain.model.MotoVehicleDomainModel;
import com.ceiba.adnparquedero.domain.model.ParkingPriceDomainModel;
import com.ceiba.adnparquedero.domain.model.VehicleDomainModel;
import com.ceiba.adnparquedero.domain.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

public class VehicleRepositoryImpl implements VehicleRepository {

    private Realm realm;

    @Inject
    public VehicleRepositoryImpl() {
        realm = Realm.getDefaultInstance();
    }

    @Override
    public boolean createParkingPriceTable(List<ParkingPriceDomainModel> priceDomainModels) {
        try (Realm realm = Realm.getDefaultInstance()) {
            List<ParkingPriceEntity> entities = new ArrayList<>();
            for (ParkingPriceDomainModel priceDomainModel : priceDomainModels) {

                //Map from domain model
                ParkingPriceEntity priceEntity = new ParkingPriceEntity();
                priceEntity.mapFromParkingPriceDomainModel(priceDomainModel);
                entities.add(priceEntity);

                //Insert locally
                realm.executeTransaction(r -> r.insertOrUpdate(entities));
            }
            return true;
        } catch (Exception e) {
            return false;
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
}