package com.BlogsProject.Functions.repositories;

import com.BlogsProject.Functions.entities.Driver;
import com.BlogsProject.Functions.entities.Truck;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TruckRepository extends JpaRepository<Truck, Integer> {

    @Query("SELECT t FROM Truck  t WHERE t.reference LIKE :reference%")
    Page<Truck> findByReference(@Param("reference") String reference, Pageable pageable);

    @Query("SELECT t FROM Truck  t WHERE t.free = false AND t.driver!= null")
    Page<Truck> findDrivedTruck(Pageable pageable);

    @Query("SELECT t FROM Truck  t WHERE t.free = true AND t.driver!= null")
    Page<Truck> findAvailableTruck(Pageable pageable);

    Optional<Truck> findTruckByRegistrationNumber(String regNumber);
}
