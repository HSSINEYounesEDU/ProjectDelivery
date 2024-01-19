package com.BlogsProject.Functions.services;


import com.BlogsProject.Functions.entities.Driver;
import com.BlogsProject.Functions.repositories.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class DriverService {

    private final DriverRepository driverRepository;

    public void create(Driver driver) throws Exception{
        Optional<Driver> drv = driverRepository.findDriverByEmail(driver.getEmail());
        if(!drv.isPresent()){
            driverRepository.save(driver);
        }
        else{
            throw new Exception("Email already in use.");
        }
    }

    public void update(Driver driver){
        driverRepository.save(driver);
    }

    public void delete(Driver driver){
        driverRepository.delete(driver);
    }

    public Driver findById(int id){
        return driverRepository.findById(id).get();
    }

    public Page<Driver> findByName(String name, int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("firstname").ascending());
        if(name.equals("all")){
            return driverRepository.findAll(pageable);
        }
        else{
            return driverRepository.findByName(name, pageable);
        }
    }

    public Page<Driver> findEnabledByName(String name, int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("firstname").ascending());
        if(name.equals("all")){
            return driverRepository.findEnabledAll(pageable);
        }
        else{
            return driverRepository.findEnabledByName(name, pageable);
        }
    }
}
