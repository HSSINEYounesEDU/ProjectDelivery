package com.BlogsProject.Functions.repositories;

import com.BlogsProject.Functions.entities.Cargo;
import com.BlogsProject.Functions.entities.Truck;
import com.BlogsProject.Functions.models.CargoRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {

    @Query("SELECT c FROM Cargo c WHERE c.truck.id= :truckId AND c.arrivingTime = null")
    Cargo findCargoByTruck(@Param("truckId") int truckId);

    @Query("SELECT c.truck FROM Cargo c WHERE c.arrivingTime != null")
    Page<Truck> getAvailableTrucks(Pageable pageable);


}
