package com.BlogsProject.Functions.controllers;

import com.BlogsProject.Functions.entities.Driver;
import com.BlogsProject.Functions.entities.Truck;
import com.BlogsProject.Functions.models.TruckRequest;
import com.BlogsProject.Functions.services.CargoService;
import com.BlogsProject.Functions.services.TruckService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/truck-api")
@RequiredArgsConstructor
public class TruckController {

    private final TruckService truckService;
    private final CargoService cargoService;

    @PostMapping("/create")
    public void create(@RequestBody TruckRequest request) throws Exception{
        truckService.create(request);
    }

    @PostMapping("/update")
    public void update(@RequestBody TruckRequest request) throws Exception{
        truckService.update(request);
    }

    @GetMapping("/get-trucks/{page}/{pageSize}/{reference}")
    public Page<Truck> getTrucks(@PathVariable("page") int page, @PathVariable("pageSize") int pageSize, @PathVariable("reference") String reference) {
        return truckService.findByReference( reference, page, pageSize);
    }

    @GetMapping("/get-available-trucks/{page}/{pageSize}/{reference}")
    public Page<Truck> getAvailableTrucks(@PathVariable("page") int page, @PathVariable("pageSize") int pageSize, @PathVariable("reference") String reference) {
        return truckService.findAvailableTruck( page, pageSize);
    }

    @GetMapping("/get-all-trucks/{page}/{pageSize}/{reference}")
    public Page<Truck> getAllTrucks(@PathVariable("page") int page, @PathVariable("pageSize") int pageSize, @PathVariable("reference") String reference) {
        return truckService.findAllTruck( page, pageSize);
    }

    @GetMapping("/get-active-trucks/{page}/{pageSize}/{reference}")
    public Page<Truck> getDrivedTrucks(@PathVariable("page") int page, @PathVariable("pageSize") int pageSize, @PathVariable("reference") String reference) {
        return truckService.findDrivedTruck( page, pageSize);
    }

}
