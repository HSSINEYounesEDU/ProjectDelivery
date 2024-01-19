package com.BlogsProject.Functions.controllers;

import com.BlogsProject.Authentication.Entity.User;
import com.BlogsProject.Functions.entities.Driver;
import com.BlogsProject.Functions.services.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/driver-api")
@RequiredArgsConstructor
public class DriverController {

    public final DriverService driverService;

    @PostMapping("/create")
    public void create(@RequestBody Driver driver) throws Exception{
        driverService.create(driver);
    }

    @GetMapping("/get-drivers/{page}/{pageSize}/{reference}")
    public Page<Driver> getDriversByName(@PathVariable("page") int page, @PathVariable("pageSize") int pageSize, @PathVariable("reference") String reference) {
        return driverService.findByName( reference, page, pageSize);
    }

    @GetMapping("/get-enabled-drivers/{page}/{pageSize}/{reference}")
    public Page<Driver> getEnabledDriversByName(@PathVariable("page") int page, @PathVariable("pageSize") int pageSize, @PathVariable("reference") String reference) {
        return driverService.findEnabledByName( reference, page, pageSize);
    }

    @GetMapping("/enable-or-disable-driver/{id}")
    public void enableOrDisableDriver(@PathVariable("id") int id){
        Driver driver = driverService.findById(id);
        if(driver.isEnabled()){
            driver.setEnabled(false);
            driverService.update(driver);
        }
        else if(!driver.isEnabled()){
            driver.setEnabled(true);
            driverService.update(driver);
        }

    }
}
