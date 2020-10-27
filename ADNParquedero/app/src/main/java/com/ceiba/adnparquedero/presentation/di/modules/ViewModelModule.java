package com.ceiba.adnparquedero.presentation.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ceiba.adnparquedero.presentation.viewmodel.FactoryViewModel;
import com.ceiba.adnparquedero.presentation.di.annotation.ViewModelKey;
import com.ceiba.adnparquedero.presentation.viewmodel.MainViewModel;
import com.ceiba.adnparquedero.presentation.viewmodel.RegisterVehicleViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FactoryViewModel factory);

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    abstract ViewModel bindMainActivityViewModel(MainViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(RegisterVehicleViewModel.class)
    abstract ViewModel bindRegisterVehicleViewModel(RegisterVehicleViewModel viewModel);
}
