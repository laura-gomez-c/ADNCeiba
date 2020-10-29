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
import com.ceiba.adnparquedero.databinding.ActivityMotoBinding;
import com.ceiba.adnparquedero.databinding.PayModalViewBinding;
import com.ceiba.adnparquedero.databinding.RegisterMotoModalViewBinding;
import com.ceiba.adnparquedero.domain.model.Moto;
import com.ceiba.adnparquedero.domain.model.Vehicle;
import com.ceiba.adnparquedero.presentation.view.adapter.MotoListAdapter;
import com.ceiba.adnparquedero.presentation.view.adapter.OnClickListenerList;
import com.ceiba.adnparquedero.presentation.viewmodel.MotoViewModel;
import com.google.common.base.Strings;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class MotoActivity extends BaseActivity implements OnClickListenerList {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ActivityMotoBinding binding;

    private MotoViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_moto);
        AndroidInjection.inject(this);

        //ViewModel
        viewModel = new ViewModelProvider(this, viewModelFactory).get(MotoViewModel.class);

        //Observers
        viewModel.listMutableLiveData.observe(this, listObserver);
        viewModel.hasValidEntryMutableLiveData.observe(this, hasValidEntryObserver);
        viewModel.hasCapacityMutableLiveData.observe(this, hasCapacityObserver);
        viewModel.motoRegisteredMutableLiveData.observe(this, motoRegisteredObserver);

        //Listeners
        binding.buttonRegisterMoto.setOnClickListener(buttonRegisterMotoClick);

        //Logic
        renderView();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_moto;
    }

    //region Listeners
    private final View.OnClickListener buttonRegisterMotoClick = v -> showRegisterModal();

    @Override
    public void listItemClickListener(Vehicle vehicle) {
        showColletPriceModal(vehicle, viewModel.collectMotoParking(vehicle));
    }
    //endregion

    //region Observers
    private final Observer<List<Moto>> listObserver = motos -> {
        MotoListAdapter motoListAdapter = new MotoListAdapter(this, motos, this);
        binding.recyclerViewMotos.setAdapter(motoListAdapter);
        binding.layoutEmptyView.setVisibility(motos.isEmpty() ? View.VISIBLE : View.GONE);
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

    private final Observer<Boolean> motoRegisteredObserver = registered -> {
        if (registered) {
            Toast.makeText(getApplicationContext(), getString(R.string.successfully_registered), Toast.LENGTH_LONG).show();
        }
    };
    //endregion

    //region Logic
    private void renderView() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerViewMotos.setLayoutManager(layoutManager);
    }

    private void showRegisterModal() {
        RegisterMotoModalViewBinding registerMotoModalViewBinding = RegisterMotoModalViewBinding.inflate(getLayoutInflater(), null, false);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        final AlertDialog modalDialog;
        dialogBuilder.setView(registerMotoModalViewBinding.getRoot()).setCancelable(true);
        modalDialog = dialogBuilder.create();

        registerMotoModalViewBinding.buttonRegister.setOnClickListener(v -> {
            if (viewModel.hasMotoCapacity()) {
                String licensePlate = registerMotoModalViewBinding.editTextLicense.getText().toString();
                String cylinderCapacity = registerMotoModalViewBinding.editTextCylinderCapacity.getText().toString();
                if (!Strings.isNullOrEmpty(licensePlate) && !Strings.isNullOrEmpty(cylinderCapacity)) {
                    viewModel.registerMoto(licensePlate, Float.parseFloat(cylinderCapacity));
                    viewModel.updateMotoList();
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
            viewModel.updateMotoList();
            modalDialog.dismiss();
        });


        modalDialog.show();
    }
    //endregion
}