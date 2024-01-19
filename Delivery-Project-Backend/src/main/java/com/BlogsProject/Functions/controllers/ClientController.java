package com.BlogsProject.Functions.controllers;

import com.BlogsProject.Authentication.Entity.User;
import com.BlogsProject.Authentication.Entity.UserRepository;
import com.BlogsProject.Functions.entities.Package;
import com.BlogsProject.Functions.repositories.PackageRepository;
import com.BlogsProject.Functions.services.PackageService;
import com.BlogsProject.Functions.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.html.Option;
import java.util.Optional;

@RestController
@RequestMapping("/users-api")
@RequiredArgsConstructor

public class ClientController {

    private final PackageService packageService;
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/get-package/{uid}/{id}")
    public Package getProduct(@PathVariable("uid") String uid, @PathVariable("id") int id) throws Exception {
        return packageService.getPackage(uid, id);
    }

    @GetMapping("/in-progress-packages/{page}/{pagesize}/{uid}")
    public Page<Package> findAll_InProgress(@PathVariable("page") int page, @PathVariable("pagesize") int pageSize, @PathVariable("uid") String uid){
        return packageService.findAll_InProgress(page, pageSize, uid);
    }

    @GetMapping("/in-progress-packages/{page}/{pagesize}/{reference}/{uid}")
    public Page<Package> findAByReference_InProgress(@PathVariable("page") int page, @PathVariable("pagesize") int pageSize, @PathVariable("reference") String reference, @PathVariable("uid") String uid){
        return packageService.findByReference_InProgress(reference, page, pageSize, uid);
    }

    @GetMapping("/delivered-packages/{page}/{pagesize}/{uid}")
    public Page<Package> findAll_Delivered(@PathVariable("page") int page, @PathVariable("pagesize") int pageSize, @PathVariable("uid") String uid){
        return packageService.findAll_Delivered(page, pageSize, uid);
    }

    @GetMapping("/delivered-packages/{page}/{pagesize}/{reference}/{uid}")
    public Page<Package> findByReference_Delivered(@PathVariable("page") int page, @PathVariable("pagesize") int pageSize, @PathVariable("reference") String reference, @PathVariable("uid") String uid){
        return packageService.findByReference_Delivered(reference, page, pageSize, uid);
    }

    @GetMapping("/get-clients-by-name/{page}/{pageSize}/{name}")
    public Page<User> findClientsByName(@PathVariable("page") int page, @PathVariable("pageSize") int pageSize, @PathVariable("name") String name){
        return userService.getClientsByName(page, pageSize, name);
    }

    @GetMapping("/get-checkpoints-by-name/{page}/{pageSize}/{name}")
    public Page<User> findCheckpointsByName(@PathVariable("page") int page, @PathVariable("pageSize") int pageSize, @PathVariable("name") String name){
        return userService.getCheckpointssByName(page, pageSize, name);
    }

    @GetMapping("/get-admins-by-name/{page}/{pageSize}/{name}")
    public Page<User> findAdminsByName(@PathVariable("page") int page, @PathVariable("pageSize") int pageSize, @PathVariable("name") String name){
        return userService.getAdminsByName(page, pageSize, name);
    }

    @GetMapping("/enable-or-disable-user/{uid}")
    public void enableOrDisableUser(@PathVariable("uid") String uid){
        Optional<User> user0 = userRepository.findById(uid);
        if(user0.isPresent()){
            User user = user0.get();
            if(user.isEnabled() == true){
                user.setEnabled(false);
                userRepository.save(user);
            }
            else if(user.isEnabled() == false){
                user.setEnabled(true);
                userRepository.save(user);
            }
        }
    }


}
