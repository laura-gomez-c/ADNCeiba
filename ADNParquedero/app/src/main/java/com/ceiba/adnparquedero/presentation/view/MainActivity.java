package com.ceiba.adnparquedero.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.ceiba.adnparquedero.R;
import com.ceiba.adnparquedero.databinding.ActivityMainBinding;
import com.ceiba.adnparquedero.domain.model.ParkingPrice;
import com.ceiba.adnparquedero.presentation.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends BaseActivity {

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

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    //region Listeners
    private final View.OnClickListener buttonRegisterCarClick = v -> {
        Intent intent = new Intent(this, CarActivity.class);
        startActivity(intent);
    };

    private final View.OnClickListener buttonRegisterMotoClick = v -> {
        Intent intent = new Intent(this, MotoActivity.class);
        startActivity(intent);
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
        List<ParkingPrice> parkingPriceList = new ArrayList<>();
        parkingPriceList.add(new ParkingPrice(1000f, ParkingPrice.CAR, ParkingPrice.HOUR));
        parkingPriceList.add(new ParkingPrice(500f, ParkingPrice.MOTO, ParkingPrice.HOUR));
        parkingPriceList.add(new ParkingPrice(8000f, ParkingPrice.CAR, ParkingPrice.DAY));
        parkingPriceList.add(new ParkingPrice(4000f, ParkingPrice.MOTO, ParkingPrice.DAY));
        mainViewModel.createPriceTable(parkingPriceList);
    }
    //endregion
}