package com.ceiba.adnparquedero.domain.usecase;

import com.ceiba.adnparquedero.domain.builder.ParkingPriceDomainModelBuilder;
import com.ceiba.adnparquedero.domain.model.ParkingPrice;
import com.ceiba.adnparquedero.domain.repository.VehicleRepository;

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
        List<ParkingPrice> modelList = new ArrayList<>();
        modelList.add(new ParkingPriceDomainModelBuilder().build());

        parkingPriceUseCase.createPriceTable(modelList);
        Mockito.verify(vehicleRepository).createParkingPriceTable(modelList);
    }
}