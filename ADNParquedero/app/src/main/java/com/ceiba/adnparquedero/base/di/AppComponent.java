package com.ceiba.adnparquedero.base.di;

import com.ceiba.adnparquedero.base.App;
import com.ceiba.adnparquedero.base.di.modules.RepositoryModule;
import com.ceiba.adnparquedero.domain.repository.VehicleRepository;
import com.ceiba.adnparquedero.domain.usecase.ParkingPriceUseCase;
import com.ceiba.adnparquedero.domain.usecase.VehicleUseCase;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RepositoryModule.class})
public interface AppComponent {

    void inject(App app);

    void inject(VehicleUseCase vehicleUseCase);

    void inject(ParkingPriceUseCase parkingPriceUseCase);

    VehicleRepository vehicleRepository();
}
