package com.ceiba.adnparquedero.presentation.di.modules;

import com.ceiba.adnparquedero.domain.repository.VehicleRepository;
import com.ceiba.adnparquedero.domain.usecase.ParkingPriceUseCase;
import com.ceiba.adnparquedero.domain.usecase.ParkingPriceUseCaseImpl;
import com.ceiba.adnparquedero.domain.usecase.VehicleUseCase;
import com.ceiba.adnparquedero.domain.usecase.VehicleUseCaseImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ViewModelModule.class, RepositoryModule.class})
public class UseCaseModule {

    @Provides
    @Singleton
    VehicleUseCase provideVehicleUseCase(VehicleRepository vehicleRepository) {
        return new VehicleUseCaseImpl(vehicleRepository);
    }

    @Provides
    @Singleton
    ParkingPriceUseCase provideParkingPriceUseCase(VehicleRepository vehicleRepository) {
        return new ParkingPriceUseCaseImpl(vehicleRepository);
    }
}