package com.ceiba.adnparquedero.presentation.di.modules;

import com.ceiba.adnparquedero.presentation.view.MainActivity;
import com.ceiba.adnparquedero.presentation.view.CarActivity;
import com.ceiba.adnparquedero.presentation.view.MotoActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public interface ActivityModule {

    @ContributesAndroidInjector()
    MainActivity contributeMainActivity();

    @ContributesAndroidInjector()
    CarActivity contributeCarActivity();

    @ContributesAndroidInjector()
    MotoActivity contributeMotoActivity();
}
