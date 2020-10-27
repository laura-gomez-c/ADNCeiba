package com.ceiba.adnparquedero.presentation.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.ceiba.adnparquedero.R;
import com.ceiba.adnparquedero.domain.model.ParkingPriceDomainModel;
import com.ceiba.adnparquedero.presentation.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MainActivity extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AndroidInjection.inject(this);

        //ViewModel
        mainViewModel = new ViewModelProvider(this, viewModelFactory).get(MainViewModel.class);

        //Logic
        initializeParkingPriceTable();
    }

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
}