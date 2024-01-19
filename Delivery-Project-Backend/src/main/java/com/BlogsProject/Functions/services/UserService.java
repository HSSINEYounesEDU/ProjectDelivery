package com.BlogsProject.Functions.services;

import com.BlogsProject.Authentication.Entity.Role;
import com.BlogsProject.Authentication.Entity.User;
import com.BlogsProject.Authentication.Entity.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public Page<User> getClientsByName(int page, int pageSize, String name){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("firstname").ascending());
        if(name.equals("all")){
            return userRepository.findAllUsers(pageable, Role.CLIENT);
        }
        else{
            return userRepository.findUsersByName(name, pageable, Role.CLIENT);
        }
    }

    public Page<User> getCheckpointssByName(int page, int pageSize, String name){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("firstname").ascending());
        if(name.equals("all")){
            return userRepository.findAllUsers(pageable, Role.CHECKPOINT);
        }
        else{
            return userRepository.findUsersByName(name, pageable, Role.CHECKPOINT);
        }
    }

    public Page<User> getAdminsByName(int page, int pageSize, String name){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("firstname").ascending());
        if(name.equals("all")){
            return userRepository.findAllUsers(pageable, Role.ADMIN);
        }
        else{
            return userRepository.findUsersByName(name, pageable, Role.ADMIN);
        }
    }
}
