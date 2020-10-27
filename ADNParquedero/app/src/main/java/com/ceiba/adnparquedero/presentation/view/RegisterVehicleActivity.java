package com.ceiba.adnparquedero.presentation.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.ceiba.adnparquedero.R;
import com.ceiba.adnparquedero.databinding.ActivityMainBinding;
import com.ceiba.adnparquedero.databinding.ActivityRegisterCarBinding;
import com.ceiba.adnparquedero.presentation.viewmodel.MainViewModel;
import com.ceiba.adnparquedero.presentation.viewmodel.RegisterVehicleViewModel;
import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class RegisterVehicleActivity extends AppCompatActivity {


    public static final String CAR_TYPE = "CAR_TYPE";
    public static final String MOTO_TYPE = "MOTO_TYPE";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ActivityRegisterCarBinding binding;

    private RegisterVehicleViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register_car);
        AndroidInjection.inject(this);

        //ViewModel
        viewModel = new ViewModelProvider(this, viewModelFactory).get(RegisterVehicleViewModel.class);

        //Listeners

        //Logic
        viewModel.vehicleType = getIntent().getExtras().get(MainActivity.VEHICLE_TYPE).toString();
        renderView();
    }

    //region Logic
    private void renderView() {
        binding.textInputLayoutCylinderCapacity.setVisibility(MOTO_TYPE.equals(viewModel.vehicleType) ? View.VISIBLE : View.GONE);
    }
    //endregion
}