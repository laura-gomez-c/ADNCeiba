package com.ceiba.adnparquedero.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.ceiba.adnparquedero.R;
import com.ceiba.adnparquedero.databinding.ActivityMainBinding;
import com.ceiba.adnparquedero.domain.model.ParkingPriceDomainModel;
import com.ceiba.adnparquedero.presentation.viewmodel.MainViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    public static final String VEHICLE_TYPE = "VEHICLE_TYPE";

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ActivityMainBinding binding;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        AndroidInjection.inject(this);

        //ViewModel
        mainViewModel = new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);

        //Listeners
        binding.buttonRegisterCar.setOnClickListener(buttonRegisterCarClick);
        binding.buttonRegisterMoto.setOnClickListener(buttonRegisterMotoClick);

        //Logic
        initializeParkingPriceTable();
    }

    //region Listeners
    private final View.OnClickListener buttonRegisterCarClick = v -> {
        if (mainViewModel.hasCarCapacity()) {
            //navigate
            Intent intent = new Intent(this, RegisterVehicleActivity.class);
            intent.putExtra(VEHICLE_TYPE, RegisterVehicleActivity.CAR_TYPE);
            startActivity(intent);
        } else {
            Snackbar.make(getCurrentFocus(), "Capacidad llena", Snackbar.LENGTH_SHORT);
        }
    };

    private final View.OnClickListener buttonRegisterMotoClick = v -> {
        if (mainViewModel.hasMotoCapacity()) {
            //navigate
            Intent intent = new Intent(this, RegisterVehicleActivity.class);
            intent.putExtra(VEHICLE_TYPE, RegisterVehicleActivity.MOTO_TYPE);
            startActivity(intent);
        } else {
            Snackbar.make(getCurrentFocus(), "Capacidad llena", Snackbar.LENGTH_SHORT);
        }
    };
    //endregion

    //region Logic

    /**
     * Valor hora carro = 1000
     * Valor hora moto = 500
     * valor día carro = 8000
     * valor día moto = 4000
     */
    //TODO: Method intended for testing, remove it when presentation implementation will be done.
    public void initializeParkingPriceTable() {
        List<ParkingPriceDomainModel> parkingPriceDomainModelList = new ArrayList<>();
        parkingPriceDomainModelList.add(new ParkingPriceDomainModel(1000f, ParkingPriceDomainModel.CAR, ParkingPriceDomainModel.HOUR));
        parkingPriceDomainModelList.add(new ParkingPriceDomainModel(500f, ParkingPriceDomainModel.MOTO, ParkingPriceDomainModel.HOUR));
        parkingPriceDomainModelList.add(new ParkingPriceDomainModel(8000f, ParkingPriceDomainModel.CAR, ParkingPriceDomainModel.DAY));
        parkingPriceDomainModelList.add(new ParkingPriceDomainModel(4000f, ParkingPriceDomainModel.MOTO, ParkingPriceDomainModel.DAY));
        mainViewModel.createPriceTable(parkingPriceDomainModelList);
    }
    //endregion
}