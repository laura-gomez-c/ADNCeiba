package com.ceiba.adnparquedero.data.repository;

import android.util.Log;

import com.ceiba.adnparquedero.data.anticorruption.CarTranslator;
import com.ceiba.adnparquedero.data.anticorruption.MotoTranslator;
import com.ceiba.adnparquedero.data.anticorruption.ParkingPriceTranslator;
import com.ceiba.adnparquedero.data.anticorruption.VehicleTranslator;
import com.ceiba.adnparquedero.data.local.model.CarEntity;
import com.ceiba.adnparquedero.data.local.model.MotoEntity;
import com.ceiba.adnparquedero.data.local.model.ParkingPriceEntity;
import com.ceiba.adnparquedero.data.local.model.VehicleEntity;
import com.ceiba.adnparquedero.domain.model.Car;
import com.ceiba.adnparquedero.domain.model.Moto;
import com.ceiba.adnparquedero.domain.model.ParkingPrice;
import com.ceiba.adnparquedero.domain.model.Vehicle;
import com.ceiba.adnparquedero.domain.repository.VehicleRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;

public class VehicleRepositoryImpl implements VehicleRepository {

    private final ParkingPriceTranslator parkingPriceTranslator;
    private final VehicleTranslator vehicleTranslator;
    private final MotoTranslator motoTranslator;
    private final CarTranslator carTranslator;

    @Inject
    public VehicleRepositoryImpl() {
        parkingPriceTranslator = new ParkingPriceTranslator();
        vehicleTranslator = new VehicleTranslator();
        motoTranslator = new MotoTranslator();
        carTranslator = new CarTranslator();
    }

    @Override
    public void createParkingPriceTable(List<ParkingPrice> priceDomainModels) {
        try (Realm realm = Realm.getDefaultInstance()) {
            List<ParkingPriceEntity> entities = new ArrayList<>();
            for (ParkingPrice parkingPrice : priceDomainModels) {
                //Map from domain model
                entities.add(parkingPriceTranslator.mapFromParkingPriceToParkingPriceEntity(parkingPrice));
            }
            //Insert locally
            realm.executeTransaction(r -> r.insertOrUpdate(entities));
            Log.d("executing..", "hecho");
        }
    }

    @Override
    public Integer getCarVehicleTypeTotalOccupancy() {
        try (Realm realm = Realm.getDefaultInstance()) {
            return realm.where(CarEntity.class).findAll().size();
        }
    }

    @Override
    public Integer getMotoVehicleTypeTotalOccupancy() {
        try (Realm realm = Realm.getDefaultInstance()) {
            return realm.where(MotoEntity.class).findAll().size();
        }
    }

    @Override
    public boolean registerCar(Car car) {
        try (Realm realm = Realm.getDefaultInstance()) {
            VehicleEntity vehicleEntity = vehicleTranslator.mapFromCarToVehicleEntity(car);
            realm.executeTransaction(r -> r.insertOrUpdate(vehicleEntity));

            CarEntity carEntity = new CarEntity(vehicleEntity);
            realm.executeTransaction(r -> r.insertOrUpdate(carEntity));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean registerMoto(Moto moto) {
        try (Realm realm = Realm.getDefaultInstance()) {
            VehicleEntity vehicleEntity = vehicleTranslator.mapFromMotoToVehicleEntity(moto);
            realm.executeTransaction(r -> r.insertOrUpdate(vehicleEntity));

            MotoEntity motoEntity = motoTranslator.mapFromMotoToMotoEntity(moto, vehicleEntity);
            realm.executeTransaction(r -> r.insertOrUpdate(motoEntity));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Car getCarVehicleByLicensePlate(String licensePlate) {
        try (Realm realm = Realm.getDefaultInstance()) {
            CarEntity carEntity = realm.where(CarEntity.class).equalTo("vehicleEntity.licensePlate", licensePlate).findFirst();
            if (carEntity == null) return null;

            return carTranslator.mapFromCarEntityToCar(carEntity);
        }
    }

    @Override
    public Moto getMotoVehicleByLicensePlate(String licensePlate) {
        try (Realm realm = Realm.getDefaultInstance()) {
            MotoEntity motoEntity = realm.where(MotoEntity.class).equalTo("vehicleEntity.licensePlate", licensePlate).findFirst();
            if (motoEntity == null) return null;

            return motoTranslator.mapFromMotoEntityToMoto(motoEntity);
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

    @Override
    public List<Car> getCarList() {
        try (Realm realm = Realm.getDefaultInstance()) {
            RealmResults<CarEntity> carEntities = realm.where(CarEntity.class).isNull("vehicleEntity.leavingTime").findAll();
            List<Car> carList = new ArrayList<>();
            for (CarEntity carEntity : carEntities) {
                carList.add(carTranslator.mapFromCarEntityToCar(carEntity));
            }

            return carList;
        }
    }

    @Override
    public List<Moto> getMotoList() {
        try (Realm realm = Realm.getDefaultInstance()) {
            RealmResults<MotoEntity> motoEntities = realm.where(MotoEntity.class).isNull("vehicleEntity.leavingTime").findAll();
            List<Moto> motoList = new ArrayList<>();
            for (MotoEntity motoEntity : motoEntities) {
                motoList.add(motoTranslator.mapFromMotoEntityToMoto(motoEntity));
            }

            return motoList;
        }
    }

    @Override
    public void takeOutVehicle(Vehicle vehicle) {
        try (Realm realm = Realm.getDefaultInstance()) {
            VehicleEntity vehicleEntity = realm.where(VehicleEntity.class).equalTo("licensePlate", vehicle.getLicensePlate()).findFirst();

            realm.executeTransaction(r -> {
                vehicleEntity.setLeavingTime(vehicle.getLeavingTime());
                r.insertOrUpdate(vehicleEntity);
            });
        }
    }
}