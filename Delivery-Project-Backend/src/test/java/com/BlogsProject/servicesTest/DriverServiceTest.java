package com.BlogsProject.servicesTest;

import com.BlogsProject.Functions.entities.Driver;
import com.BlogsProject.Functions.repositories.DriverRepository;
import com.BlogsProject.Functions.services.DriverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DriverServiceTest {

    @Mock
    private DriverRepository driverRepository;

    @InjectMocks
    private DriverService driverService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() throws Exception {
        Driver driver = new Driver();
        driver.setEmail("test@example.com");

        when(driverRepository.findDriverByEmail(anyString())).thenReturn(Optional.empty());

        driverService.create(driver);

        verify(driverRepository, times(1)).save(driver);
    }

    @Test
    void testUpdate() {
        Driver driver = new Driver();

        driverService.update(driver);

        verify(driverRepository, times(1)).save(driver);
    }

    @Test
    void testDelete() {
        Driver driver = new Driver();

        driverService.delete(driver);

        verify(driverRepository, times(1)).delete(driver);
    }

    @Test
    void testFindById() {
        when(driverRepository.findById(anyInt())).thenReturn(Optional.of(new Driver()));

        driverService.findById(1);

        verify(driverRepository, times(1)).findById(1);
    }

    @Test
    void testFindByName() {
        Page<Driver> page = mock(Page.class);
        when(driverRepository.findAll(any(Pageable.class))).thenReturn(page);
        when(driverRepository.findByName(anyString(), any(Pageable.class))).thenReturn(page);

        driverService.findByName("test", 0, 5);

        verify(driverRepository, times(1)).findByName(eq("test"), any(Pageable.class));
    }


    @Test
    void testFindEnabledByName() {
        Page<Driver> page = mock(Page.class);
        when(driverRepository.findEnabledAll(any(Pageable.class))).thenReturn(page);
        when(driverRepository.findEnabledByName(anyString(), any(Pageable.class))).thenReturn(page);

        driverService.findEnabledByName("test", 0, 5);

        verify(driverRepository, times(1)).findEnabledByName(eq("test"), any(Pageable.class));
    }

}
