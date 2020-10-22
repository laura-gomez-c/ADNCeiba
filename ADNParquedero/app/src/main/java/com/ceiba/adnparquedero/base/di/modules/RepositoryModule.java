package com.ceiba.adnparquedero.base.di.modules;

import com.ceiba.adnparquedero.data.repository.VehicleRepositoryImpl;
import com.ceiba.adnparquedero.domain.repository.VehicleRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RepositoryModule {

    @Binds
    abstract VehicleRepository vehicleRepository(VehicleRepositoryImpl repositorioUsuarioImpl);
}