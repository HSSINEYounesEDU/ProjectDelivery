package com.BlogsProject.Functions.controllers;


import com.BlogsProject.Functions.entities.Cargo;
import com.BlogsProject.Functions.entities.Truck;
import com.BlogsProject.Functions.models.CargoRequest;
import com.BlogsProject.Functions.services.CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cargo-api")
@RequiredArgsConstructor
public class CargoCotroller {

    private final CargoService cargoService;

    @PostMapping("/create")
    public void create(@RequestBody CargoRequest request){
        cargoService.create(request);
    }

    @GetMapping ("/receive/{id}")
    public void create(@PathVariable int id){
        cargoService.receiveCargo(id);
    }

    @GetMapping("/get-cargo-by-truck-id/{truckId}")
    public Cargo getCargo(@PathVariable("truckId") int truckId){
        return cargoService.getCargo(truckId);
    }

    @GetMapping("/get-active-trucks/{page}/{pageSize}")
    public Page<Truck> getActiveTrucks(@PathVariable("page") int page, @PathVariable("pageSize") int pageSize){
        return cargoService.getActiveTrucks(page, pageSize);
    }
}
