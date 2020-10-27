package com.ceiba.adnparquedero.presentation.di.modules;

import com.ceiba.adnparquedero.data.repository.VehicleRepositoryImpl;
import com.ceiba.adnparquedero.domain.repository.VehicleRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    //@Binds
    //VehicleRepository vehicleRepository(VehicleRepositoryImpl repositorioUsuarioImpl);

    @Provides
    @Singleton
    VehicleRepository provideUserRepository() {
        return new VehicleRepositoryImpl();
    }
}