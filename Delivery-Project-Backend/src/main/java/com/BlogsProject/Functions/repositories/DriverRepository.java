package com.BlogsProject.Functions.repositories;

import com.BlogsProject.Functions.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Integer> {

    @Query("SELECT d FROM Driver d WHERE d.lastname LIKE :name% OR d.firstname LIKE :name%")
    Page<Driver> findByName(@Param("name") String name, Pageable pageable);

    @Query("SELECT d FROM Driver d WHERE d.enabled = true and d.free = true")
    Page<Driver> findEnabledAll( Pageable pageable);

    @Query("SELECT d FROM Driver d WHERE (d.lastname LIKE :name% OR d.firstname LIKE :name%) AND d.enabled = true AND d.free = true")
    Page<Driver> findEnabledByName(@Param("name") String name, Pageable pageable);

    Optional<Driver> findDriverByEmail(String email);
}
