package com.BlogsProject.servicesTest;

import com.BlogsProject.Functions.entities.*;
import com.BlogsProject.Functions.entities.Package;
import com.BlogsProject.Functions.models.CargoRequest;
import com.BlogsProject.Functions.repositories.CargoRepository;
import com.BlogsProject.Functions.repositories.PackageRepository;
import com.BlogsProject.Functions.repositories.TrajectRepository;
import com.BlogsProject.Functions.repositories.TruckRepository;
import com.BlogsProject.Functions.services.CargoService;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class CargoServiceTest {

    @Mock
    private CargoRepository cargoRepository;

    @Mock
    private TruckRepository truckRepository;

    @Mock
    private PackageRepository packageRepository;

    @InjectMocks
    private CargoService cargoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        CargoRequest request = new CargoRequest();
        request.setPackages(new int[]{1, 2});
        request.setTruckId(1);

        Package mockPackage1 = new Package();
        mockPackage1.setId(1);
        Package mockPackage2 = new Package();
        mockPackage2.setId(2);

        Truck mockTruck = new Truck();
        mockTruck.setId(1);
        mockTruck.setFree(true);

        when(packageRepository.getReferenceById(1)).thenReturn(mockPackage1);
        when(packageRepository.getReferenceById(2)).thenReturn(mockPackage2);
        when(truckRepository.getReferenceById(1)).thenReturn(mockTruck);

        cargoService.create(request);

        verify(packageRepository, times(2)).getReferenceById(1);
        verify(packageRepository, times(2)).getReferenceById(2);
        verify(truckRepository, times(1)).getReferenceById(1);
        verify(truckRepository, times(1)).save(mockTruck);

        Assertions.assertEquals(State.ONROAD, mockPackage1.getState());
        Assertions.assertEquals(State.ONROAD, mockPackage2.getState());

        verify(cargoRepository, times(1)).save(any(Cargo.class));
    }


    @Test
    void testReceiveCargo() {

        int cargoId = 1;
        Cargo mockCargo = new Cargo();
        Truck mockTruck = new Truck();
        Traject mockTraject = new Traject();
        mockTraject.setRoad(new ArrayList<City>());


        mockTraject.getRoad().add(City.CASABLANCA);
        mockTraject.getRoad().add(City.MARRAKECH);

        mockCargo.setId(cargoId);
        mockCargo.setTruck(mockTruck);

        List<Package> packages = new ArrayList<>();
        Package mockPackage = new Package();
        mockPackage.setTraject(mockTraject);
        packages.add(mockPackage);
        mockCargo.setPackages(packages);

        when(cargoRepository.getReferenceById(cargoId)).thenReturn(mockCargo);
        when(truckRepository.getReferenceById(anyInt())).thenReturn(mockTruck);
        when(packageRepository.getReferenceById(anyInt())).thenReturn(mockPackage);

        cargoService.receiveCargo(cargoId);

        verify(cargoRepository, times(1)).getReferenceById(cargoId);
        verify(truckRepository, times(1)).save(mockTruck);
        for (Package aPackage : packages) {
            verify(packageRepository, times(1)).save(aPackage);
        }
    }


    @Test
    void testGetCargo() {

        int cargoId = 1;
        Cargo mockCargo = new Cargo();
        mockCargo.setId(cargoId);

        when(cargoRepository.findCargoByTruck(cargoId)).thenReturn(mockCargo);

        Cargo result = cargoService.getCargo(cargoId);

        verify(cargoRepository, times(1)).findCargoByTruck(cargoId);
        Assertions.assertNotNull(result);
    }

    @Test
    void testGetActiveTrucks() {
        List<Truck> trucks = Arrays.asList(new Truck(), new Truck());
        Page<Truck> page = new PageImpl<>(trucks);

        when(cargoRepository.getAvailableTrucks(any())).thenReturn(page);

        Page<Truck> result = cargoService.getActiveTrucks(0, 10);

        verify(cargoRepository, times(1)).getAvailableTrucks(any());
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.getContent().size());
    }
}
