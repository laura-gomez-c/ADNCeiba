package com.ceiba.adnparquedero.domain.usecase;

import com.ceiba.adnparquedero.domain.builder.CarVehicleDomainModelBuilder;
import com.ceiba.adnparquedero.domain.builder.ParkingPriceDomainModelBuilder;
import com.ceiba.adnparquedero.domain.model.CarVehicleDomainModel;
import com.ceiba.adnparquedero.domain.model.ParkingPriceDomainModel;
import com.ceiba.adnparquedero.domain.repository.VehicleRepository;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

public class ParkingPriceUseCaseImplTest {

    @Mock
    private VehicleRepository vehicleRepository;

    private ParkingPriceUseCase parkingPriceUseCase;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        parkingPriceUseCase = new ParkingPriceUseCaseImpl(vehicleRepository);
    }

    @Test
    public void createPriceTableTest() {
        List<ParkingPriceDomainModel> modelList = new ArrayList<>();
        modelList.add(new ParkingPriceDomainModelBuilder().build());

        parkingPriceUseCase.createPriceTable(modelList);
        Mockito.verify(vehicleRepository).createParkingPriceTable(modelList);
    }
}