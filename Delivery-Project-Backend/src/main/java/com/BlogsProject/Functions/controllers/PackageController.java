package com.BlogsProject.Functions.controllers;

import com.BlogsProject.Functions.entities.Package;
import com.BlogsProject.Functions.models.PackageRequest;
import com.BlogsProject.Functions.services.PackageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/package-api")
@RequiredArgsConstructor
public class PackageController {

    private final PackageService packageService;

    @PostMapping("/create")
    public void create(@RequestBody PackageRequest request) throws Exception{
        packageService.create(request);
    }

    @GetMapping("/get-in-progress-packages/{page}/{pageSize}/{ref}")
    public Page<Package> getInProgressPackagesByRef(@PathVariable("page") int page, @PathVariable("pageSize") int pageSize, @PathVariable("ref") String ref){
        return packageService.findInProgressByPackageReference(ref, page, pageSize);
    }

    @GetMapping("/get-delevired-packages/{page}/{pageSize}/{ref}")
    public Page<Package> getDeliveredPackagesByRef(@PathVariable("page") int page, @PathVariable("pageSize") int pageSize, @PathVariable("ref") String ref){
        return packageService.findDeliveredByPackageReference(ref, page, pageSize);
    }

    @GetMapping("/get-in-chekpoints-packages/{page}/{pageSize}/{ref}")
    public Page<Package> getInProgressInCheckpoint(@PathVariable("page") int page, @PathVariable("pageSize") int pageSize, @PathVariable("ref") String ref){
        return packageService.findInProgressOnCkeckpoints(ref, page, pageSize);
    }
}
