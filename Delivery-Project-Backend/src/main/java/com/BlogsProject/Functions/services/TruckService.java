package com.BlogsProject.Functions.services;

import com.BlogsProject.Functions.entities.Driver;
import com.BlogsProject.Functions.entities.Truck;
import com.BlogsProject.Functions.models.CargoRequest;
import com.BlogsProject.Functions.models.TruckRequest;
import com.BlogsProject.Functions.repositories.CargoRepository;
import com.BlogsProject.Functions.repositories.DriverRepository;
import com.BlogsProject.Functions.repositories.TruckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TruckService {

    private final TruckRepository truckRepository;
    private final DriverRepository driverRepository;
    private final CargoRepository cargoRepository;

    public void create(TruckRequest request) throws Exception{
        Optional<Truck> trk = truckRepository.findTruckByRegistrationNumber(request.getRegistrationNumber());
        Optional<Driver> drv = driverRepository.findById(request.getDriver());
        if(request.getDriver() == 0){
            if(!trk.isPresent()){
                Truck truck = Truck.builder()
                        .reference(request.getReference())
                        .registrationNumber(request.getRegistrationNumber())
                        .build();
                truckRepository.save(truck);
            }
            else{
                throw new Exception("Reference already in use.");
            }
        }
        else{
            if(!trk.isPresent() && drv.isPresent()){
                Driver drv1 = drv.get();
                drv1.setFree(false);
                driverRepository.save(drv1);
                Truck truck = Truck.builder()
                        .reference(request.getReference())
                        .registrationNumber(request.getRegistrationNumber())
                        .driver(drv.get())
                        .build();
                truckRepository.save(truck);
            }
            else{
                throw new Exception("Reference already in use.");
            }
        }

    }

    public void update(TruckRequest request) throws Exception{
        Optional<Truck> trk = truckRepository.findTruckByRegistrationNumber(request.getRegistrationNumber());
        if(request.getDriver() != 0 ){
            Optional<Driver> drv = driverRepository.findById(request.getDriver());
            Driver drv1 = drv.get();
            drv1.setFree(false);
            driverRepository.save(drv1);
            if(trk.isPresent()){
                Truck truck = trk.get();
                truck.setDriver(drv.get());
                truckRepository.save(truck);
            }
            else{
                throw new Exception("Reference already in use.");
            }
        }
        else{
            if(trk.isPresent()){
                Truck truck = trk.get();
                Driver drv = truck.getDriver();
                drv.setFree(true);
                driverRepository.save(drv);
                truck.setDriver(null);
                truckRepository.save(truck);
            }
            else{
                throw new Exception("Reference already in use.");
            }
        }
    }

    public void delete(Truck truck){
        truckRepository.delete(truck);
    }

    public Page<Truck> findByReference(String reference, int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        if(reference.equals("all")){
            return truckRepository.findAll(pageable);
        }
        else{
            return truckRepository.findByReference(reference, pageable);
        }
    }

    public Page<Truck> findAllTruck(int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        return truckRepository.findAll(pageable);
    }

    public Page<Truck> findAvailableTruck(int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        return truckRepository.findAvailableTruck(pageable);
    }

    public Page<Truck> findDrivedTruck(int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        return truckRepository.findDrivedTruck(pageable);
    }

}
