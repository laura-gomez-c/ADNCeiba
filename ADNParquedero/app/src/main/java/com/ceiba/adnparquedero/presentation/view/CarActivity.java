package com.ceiba.adnparquedero.presentation.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ceiba.adnparquedero.R;
import com.ceiba.adnparquedero.databinding.ActivityCarBinding;
import com.ceiba.adnparquedero.databinding.PayModalViewBinding;
import com.ceiba.adnparquedero.databinding.RegisterCarModalViewBinding;
import com.ceiba.adnparquedero.domain.model.Car;
import com.ceiba.adnparquedero.domain.model.Vehicle;
import com.ceiba.adnparquedero.presentation.view.adapter.CarListAdapter;
import com.ceiba.adnparquedero.presentation.view.adapter.OnClickListenerList;
import com.ceiba.adnparquedero.presentation.viewmodel.CarViewModel;
import com.google.common.base.Strings;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class CarActivity extends BaseActivity implements OnClickListenerList {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ActivityCarBinding binding;

    private CarViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_car);
        AndroidInjection.inject(this);

        //ViewModel
        viewModel = new ViewModelProvider(this, viewModelFactory).get(CarViewModel.class);

        //Observers
        viewModel.listMutableLiveData.observe(this, listObserver);
        viewModel.hasValidEntryMutableLiveData.observe(this, hasValidEntryObserver);
        viewModel.hasCapacityMutableLiveData.observe(this, hasCapacityObserver);
        viewModel.carRegisteredMutableLiveData.observe(this, carRegisteredObserver);

        //Listeners
        binding.buttonRegisterCar.setOnClickListener(buttonRegisterCarClick);


        //Logic
        renderView();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_car;
    }


    //region Observers
    private final Observer<List<Car>> listObserver = cars -> {
        CarListAdapter carListAdapter = new CarListAdapter(this, cars, this);
        binding.recyclerViewCars.setAdapter(carListAdapter);
        binding.layoutEmptyView.setVisibility(cars.isEmpty() ? View.VISIBLE : View.GONE);
    };

    private final Observer<Boolean> hasValidEntryObserver = isEntryValid -> {
        if (!isEntryValid) {
            Toast.makeText(getApplicationContext(), getString(R.string.not_authorized), Toast.LENGTH_SHORT).show();
        }
    };

    private final Observer<Boolean> hasCapacityObserver = hasCapacity -> {
        if (!hasCapacity) {
            Toast.makeText(getApplicationContext(), getString(R.string.no_capacity), Toast.LENGTH_SHORT).show();
        }
    };

    private final Observer<Boolean> carRegisteredObserver = registered -> {
        if (registered) {
            Toast.makeText(getApplicationContext(), getString(R.string.successfully_registered), Toast.LENGTH_LONG).show();
        }
    };
    //endregion

    //region Listeners
    private final View.OnClickListener buttonRegisterCarClick = v -> showRegisterModal();

    @Override
    public void listItemClickListener(Vehicle vehicle) {
        showColletPriceModal(vehicle,  viewModel.collectCarParking(vehicle));
    }
    //endregion

    //region Logic
    private void renderView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerViewCars.setLayoutManager(layoutManager);
    }

    private void showRegisterModal() {
        RegisterCarModalViewBinding modalViewBinding = RegisterCarModalViewBinding.inflate(getLayoutInflater(), null, false);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final AlertDialog modalDialog;
        dialogBuilder.setView(modalViewBinding.getRoot()).setCancelable(true);
        modalDialog = dialogBuilder.create();

        modalViewBinding.buttonRegister.setOnClickListener(v -> {
            if (viewModel.hasCarCapacity()) {
                String licensePlate = modalViewBinding.editTextLicense.getText().toString();
                if (!Strings.isNullOrEmpty(licensePlate)) {
                    viewModel.registerCar(licensePlate);
                    viewModel.updateCarList();
                }
            }

            modalDialog.dismiss();
        });


        modalDialog.show();
    }

    private void showColletPriceModal(Vehicle vehicle, String price) {
        PayModalViewBinding payModalViewBinding = PayModalViewBinding.inflate(getLayoutInflater(), null, false);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final AlertDialog modalDialog;
        dialogBuilder.setView(payModalViewBinding.getRoot()).setCancelable(true);
        modalDialog = dialogBuilder.create();
        payModalViewBinding.textLicensePlate.setText(vehicle.getLicensePlate());
        payModalViewBinding.textPrice.setText("$ ".concat(price));

        payModalViewBinding.buttonPay.setOnClickListener(v -> {
            viewModel.takeOutVehicle(vehicle);
            viewModel.updateCarList();
            modalDialog.dismiss();
        });


        modalDialog.show();
    }
    //endregion
}