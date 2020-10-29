package com.ceiba.adnparquedero.domain.usecase;

import com.ceiba.adnparquedero.domain.builder.CarVehicleDomainModelBuilder;
import com.ceiba.adnparquedero.domain.builder.MotoVehicleDomainModelBuilder;
import com.ceiba.adnparquedero.domain.common.util.CalendarOperatorUtil;
import com.ceiba.adnparquedero.domain.model.Car;
import com.ceiba.adnparquedero.domain.model.Moto;
import com.ceiba.adnparquedero.domain.model.Vehicle;
import com.ceiba.adnparquedero.domain.repository.VehicleRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;

public class VehicleUseCaseImplTest {

    private VehicleUseCase vehicleUseCase;

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private VehicleUseCaseImpl vehicleUseCaseImpl;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        vehicleUseCase = new VehicleUseCaseImpl(vehicleRepository);
    }

    @Test
    public void registerCarTest() {
        Car car = new CarVehicleDomainModelBuilder().build();

        vehicleUseCase.registerCar(car);
        Mockito.verify(vehicleRepository).registerCar(car);
    }

    @Test
    public void registerMotoTest() {
        Moto moto = new MotoVehicleDomainModelBuilder().build();

        vehicleUseCase.registerMoto(moto);
        Mockito.verify(vehicleRepository).registerMoto(moto);
    }

    @Test
    public void hasCarCapacityTest_shouldBe_true() {
        Mockito.when(vehicleRepository.getCarVehicleTypeTotalOccupancy()).thenReturn(10);

        boolean hasCarCapacity = vehicleUseCase.hasCarCapacity();

        assert (hasCarCapacity);
    }

    @Test
    public void hasCarCapacityTest_shouldBe_false() {
        Mockito.when(vehicleRepository.getCarVehicleTypeTotalOccupancy()).thenReturn(20);

        boolean hasCarCapacity = vehicleUseCase.hasCarCapacity();

        assert (!hasCarCapacity);
    }

    @Test
    public void hasMotoCapacityTest_shouldBe_true() {
        Mockito.when(vehicleRepository.getMotoVehicleTypeTotalOccupancy()).thenReturn(5);

        boolean hasMotoCapacity = vehicleUseCase.hasMotoCapacity();

        assert (hasMotoCapacity);
    }

    @Test
    public void hasMotoCapacityTest_shouldBe_false() {
        Mockito.when(vehicleRepository.getMotoVehicleTypeTotalOccupancy()).thenReturn(10);

        boolean hasMotoCapacity = vehicleUseCase.hasMotoCapacity();

        assert (!hasMotoCapacity);
    }

    @Test
    public void collectCarParking() {
        Calendar calendar = Calendar.getInstance();
        String arrivingTime = CalendarOperatorUtil.parseCalendarToString(calendar, Vehicle.DATE_TIME);

        calendar.add(Calendar.HOUR, 2);
        String leavingTime = CalendarOperatorUtil.parseCalendarToString(calendar, Vehicle.DATE_TIME);

        Car car = new CarVehicleDomainModelBuilder().withArrivingTime(arrivingTime).withLeavingTime(leavingTime).build();
        Mockito.when(vehicleRepository.getCarVehicleByLicensePlate(car.getLicensePlate())).thenReturn(car);
        Mockito.when(vehicleUseCaseImpl.calculateParkingHours(car)).thenReturn(2f);
        Mockito.when(vehicleRepository.getParkingPrice("CAR", "HOUR")).thenReturn(10f);
        Mockito.when(vehicleRepository.getParkingPrice("CAR", "DAY")).thenReturn(10f);

        String carParkingPrice = vehicleUseCase.collectCarParking(car.getLicensePlate());

    }
}