package com.BlogsProject.Functions.services;

import com.BlogsProject.Functions.entities.*;
import com.BlogsProject.Functions.entities.Package;
import com.BlogsProject.Functions.models.CargoRequest;
import com.BlogsProject.Functions.models.PackageRequest;
import com.BlogsProject.Functions.models.TruckRequest;
import com.BlogsProject.Functions.repositories.CargoRepository;
import com.BlogsProject.Functions.repositories.PackageRepository;
import com.BlogsProject.Functions.repositories.TrajectRepository;
import com.BlogsProject.Functions.repositories.TruckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CargoService {

    private final CargoRepository cargoRepository;
    private final TruckRepository truckRepository;
    private final PackageRepository packageRepository;
    private final TrajectRepository trajectRepository;

    public void create(CargoRequest request){
        List<Package> list = new ArrayList<>();
        for (int i = 0; i < request.getPackages().length; i++){
            list.add(packageRepository.getReferenceById(request.getPackages()[i]));
            Package pck = packageRepository.getReferenceById(request.getPackages()[i]);
            pck.setState(State.ONROAD);
        }
        Truck truck = truckRepository.getReferenceById((request.getTruckId()));
        truck.setFree(false);
        truckRepository.save(truck);
        Cargo cargo = Cargo.builder()
                .truck(truck)
                .creationTime(new Date())
                .packages(list)
                .build();
        cargoRepository.save(cargo);
    }

    public void receiveCargo(int id){
        Cargo cargo = cargoRepository.getReferenceById(id);
        cargo.setArrivingTime(new Date());
        List<Package> list = cargo.getPackages();

        Truck truck = cargo.getTruck();
        truck.setFree(true);
        truckRepository.save(truck);

        for(Package p: list){
            p.setState(State.ONCHECKPOINT);
            City current = p.getPresentLocation();
            Traject traject = p.getTraject();

            if(traject.getRoad().contains(current)){
                for(int i = 0; i < traject.getRoad().size() - 1; i++){
                    if(traject.getRoad().get(i).equals(p.getPresentLocation())){
                        p.setPresentLocation(traject.getRoad().get(i+1));
                    }
                }
            }
            else{
                p.setPresentLocation(traject.getRoad().get(0));
            }
            if(p.getPresentLocation().equals(p.getTraject().getDestination())){
                p.setDelivered(true);
                p.setDeliveryDate(new Date());
            }
            packageRepository.save(p);
        }
    }

    public Cargo getCargo(int id){
        return cargoRepository.findCargoByTruck(id);
    }

    public Page<Truck> getActiveTrucks(int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("id").ascending());
        return cargoRepository.getAvailableTrucks(pageable);
    }
}
