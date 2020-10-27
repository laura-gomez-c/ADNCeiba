package com.ceiba.adnparquedero.presentation.di;

import android.app.Application;

import com.ceiba.adnparquedero.App;
import com.ceiba.adnparquedero.presentation.di.modules.ActivityModule;
import com.ceiba.adnparquedero.presentation.di.modules.RepositoryModule;
import com.ceiba.adnparquedero.presentation.di.modules.UseCaseModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {RepositoryModule.class, ActivityModule.class, UseCaseModule.class, AndroidSupportInjectionModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(App app);
}
