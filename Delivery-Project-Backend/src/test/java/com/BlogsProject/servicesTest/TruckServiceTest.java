package com.BlogsProject.servicesTest;

import com.BlogsProject.Functions.entities.Driver;
import com.BlogsProject.Functions.entities.Truck;
import com.BlogsProject.Functions.models.TruckRequest;
import com.BlogsProject.Functions.repositories.CargoRepository;
import com.BlogsProject.Functions.repositories.DriverRepository;
import com.BlogsProject.Functions.repositories.TruckRepository;
import com.BlogsProject.Functions.services.TruckService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@Service
@RequiredArgsConstructor
public class TruckServiceTest {

    @Mock
    private TruckRepository truckRepository;

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private CargoRepository cargoRepository;

    @InjectMocks
    private TruckService truckService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateTruckWithoutDriver() throws Exception {

        TruckRequest request = new TruckRequest();
        request.setRegistrationNumber("ABC123");
        request.setDriver(0);

        when(truckRepository.findTruckByRegistrationNumber(request.getRegistrationNumber()))
                .thenReturn(Optional.empty());

        truckService.create(request);

        verify(truckRepository, times(1)).save(any(Truck.class));
    }

    @Test
    void testCreateTruckWithDriver() throws Exception {
        // Arrange
        TruckRequest request = new TruckRequest();
        request.setRegistrationNumber("ABC123");
        request.setDriver(1); // Example driver ID
        // Set other fields of request

        when(truckRepository.findTruckByRegistrationNumber(request.getRegistrationNumber()))
                .thenReturn(Optional.empty());
        when(driverRepository.findById(request.getDriver()))
                .thenReturn(Optional.of(new Driver())); // Mock driver

        // Act
        truckService.create(request);

        // Assert
        verify(truckRepository, times(1)).save(any(Truck.class));
        verify(driverRepository, times(1)).save(any(Driver.class));
    }


    @Test
    void testUpdateTruckWithDriver() throws Exception {

        TruckRequest request = new TruckRequest();
        request.setRegistrationNumber("REG123");
        request.setDriver(1);

        Truck truck = new Truck();
        Driver driver = new Driver();
        driver.setFree(true);

        when(truckRepository.findTruckByRegistrationNumber("REG123")).thenReturn(Optional.of(truck));
        when(driverRepository.findById(1)).thenReturn(Optional.of(driver));

        truckService.update(request);

        verify(truckRepository, times(1)).save(truck);
        verify(driverRepository, times(1)).save(driver);
        assertFalse(driver.isFree());
        assertEquals(driver, truck.getDriver());
    }

    @Test
    void testUpdateTruckWithoutDriver() throws Exception {
        TruckRequest request = new TruckRequest();
        request.setRegistrationNumber("REG123");
        request.setDriver(0);

        Truck truck = new Truck();
        Driver driver = new Driver();
        driver.setFree(false);
        truck.setDriver(driver);

        when(truckRepository.findTruckByRegistrationNumber("REG123")).thenReturn(Optional.of(truck));

        truckService.update(request);

        verify(truckRepository, times(1)).save(truck);
        verify(driverRepository, times(1)).save(driver);
        assertTrue(driver.isFree());
        assertNull(truck.getDriver());
    }

    @Test
    void testDeleteTruck() {

        Truck truck = new Truck();
        truck.setRegistrationNumber("REG123");

        truckService.delete(truck);

        verify(truckRepository, times(1)).delete(truck);
    }

    @Test
    void testFindByReferenceAll() {

        String reference = "all";
        int page = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        Page<Truck> expectedPage = mock(Page.class);

        when(truckRepository.findAll(pageable)).thenReturn(expectedPage);


        Page<Truck> result = truckService.findByReference(reference, page, pageSize);

        verify(truckRepository, times(1)).findAll(pageable);
        assertEquals(expectedPage, result);
    }


    @Test
    void testFindBySpecificReference() {
        String reference = "specificReference";
        int page = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        Page<Truck> expectedPage = mock(Page.class);

        when(truckRepository.findByReference(reference, pageable)).thenReturn(expectedPage);

        Page<Truck> result = truckService.findByReference(reference, page, pageSize);

        verify(truckRepository, times(1)).findByReference(reference, pageable);
        assertEquals(expectedPage, result);
    }

    @Test
    void testFindAllTruck() {

        int page = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        Page<Truck> expectedPage = mock(Page.class);
        when(truckRepository.findAll(pageable)).thenReturn(expectedPage);

        Page<Truck> result = truckService.findAllTruck(page, pageSize);

        verify(truckRepository, times(1)).findAll(pageable);
        assertEquals(expectedPage, result);
    }

    @Test
    void testFindAvailableTruck() {
        int page = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        Page<Truck> expectedPage = mock(Page.class);
        when(truckRepository.findAvailableTruck(pageable)).thenReturn(expectedPage);

        Page<Truck> result = truckService.findAvailableTruck(page, pageSize);

        verify(truckRepository, times(1)).findAvailableTruck(pageable);
        assertEquals(expectedPage, result);
    }

    @Test
    void testFindDrivedTruck() {

        int page = 0;
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        Page<Truck> expectedPage = mock(Page.class);
        when(truckRepository.findDrivedTruck(pageable)).thenReturn(expectedPage);

        Page<Truck> result = truckService.findDrivedTruck(page, pageSize);

        verify(truckRepository, times(1)).findDrivedTruck(pageable);
        assertEquals(expectedPage, result);
    }

}
