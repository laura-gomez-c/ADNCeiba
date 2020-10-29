package com.ceiba.adnparquedero.presentation.di.modules;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ceiba.adnparquedero.presentation.viewmodel.CarViewModel;
import com.ceiba.adnparquedero.presentation.viewmodel.FactoryViewModel;
import com.ceiba.adnparquedero.presentation.di.annotation.ViewModelKey;
import com.ceiba.adnparquedero.presentation.viewmodel.MainViewModel;
import com.ceiba.adnparquedero.presentation.viewmodel.MotoViewModel;

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
    abstract ViewModel bindMainViewModel(MainViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(CarViewModel.class)
    abstract ViewModel bindCarViewModel(CarViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MotoViewModel.class)
    abstract ViewModel bindMotoViewModel(MotoViewModel viewModel);
}
