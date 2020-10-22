package com.ceiba.adnparquedero.base.di.modules;

import com.ceiba.adnparquedero.data.repository.VehicleRepositoryImpl;
import com.ceiba.adnparquedero.domain.repository.VehicleRepository;

import dagger.Binds;
import dagger.Module;

@Module
public interface RepositoryModule {

    @Binds
    VehicleRepository vehicleRepository(VehicleRepositoryImpl repositorioUsuarioImpl);
}