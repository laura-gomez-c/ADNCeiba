package com.ceiba.adnparquedero.domain.usecase;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.ceiba.adnparquedero.domain.repository.VehicleRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VehicleUseCaseTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private VehicleRepository vehicleRepository;

    private VehicleUseCase vehicleUseCase;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vehicleUseCase = new VehicleUseCase();
    }

    @Test
    public void testRegisterCar() {
        when(vehicleRepository.getCarVehicleTypeTotalOccupancy()).thenReturn(10);
        boolean response = vehicleUseCase.hasCarCapacity();
        assert (response);
    }

    public void testRegisterMoto() {
    }

    public void testGetArrivingTime() {
    }

    public void testHasCarCapacity() {
    }

    public void testHasMotoCapacity() {
    }

    public void testHasValidEntryByDay() {
    }

    public void testCollectCarParking() {
    }

    public void testCollectMotoParking() {
    }

    public void testCalculateParkingHours() {
    }

    public void testCalculateVehicleParkingPrice() {
    }
}