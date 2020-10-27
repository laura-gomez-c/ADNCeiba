package com.ceiba.adnparquedero.presentation.di.modules;

import com.ceiba.adnparquedero.presentation.view.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Component ID: ActivityModule.java <br>
 * Description: By default, the only one activity of the app will use dependency injection.  <br>
 * Author: <a href="mailto:lauragomez.lg247@gmail.com">Laura Gómez</a>
 * <p/>
 * Revision Change
 * <table>
 * <tr>
 * <th>Author</th><th>Date</th><th>Version</th><th>Change-Description</th>
 * </tr>
 * <tr>
 * <td>Laura Gómez</td><td>9/12/2020</td><td>1.0</td><td>Initial</td>
 * </tr>
 * </table>
 */
@Module
public interface ActivityModule {

    @ContributesAndroidInjector()
    MainActivity contributeMainActivity();
}
