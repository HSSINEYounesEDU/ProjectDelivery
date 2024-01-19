package com.BlogsProject.Functions.repositories;

import com.BlogsProject.Functions.entities.Package;
import com.BlogsProject.Functions.entities.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PackageRepository extends JpaRepository<Package, Integer> {

    @Query("SELECT p FROM Package p WHERE (p.reference LIKE :reference%) AND p.delivered = false")
    Page<Package> findInProgressByReference(@Param("reference") String reference, Pageable pageable);

    @Query("SELECT p FROM Package p WHERE p.delivered = false")
    Page<Package> findAllInProgress( Pageable pageable);

    @Query("SELECT p FROM Package p WHERE (p.reference LIKE :reference%) AND p.delivered = true")
    Page<Package> findDeliveredByReference(@Param("reference") String reference, Pageable pageable);

    @Query("SELECT p FROM Package p WHERE p.delivered = true")
    Page<Package> findAllDelivered( Pageable pageable);

    @Query("SELECT p FROM Package p WHERE (p.reference LIKE :reference%) AND p.delivered = false AND p.state= :state")
    Page<Package> findInProgressInCheckpoints(@Param("reference") String reference, @Param("state")State state, Pageable pageable);

    @Query("SELECT p FROM Package p WHERE p.delivered = false AND p.state= :state")
    Page<Package> findAllInProgressInCheckpoints( @Param("state")State state, Pageable pageable);


//              CLIENT REQUESTS
    @Query("SELECT p FROM Package p WHERE p.delivered = false AND p.client.uid = :uid")
    Page<Package> findAll_InProgress(@Param("uid") String uid, Pageable pageable);

    @Query("SELECT p FROM Package p WHERE p.delivered = false AND p.reference LIKE :reference% AND p.client.uid = :uid")
    Page<Package> findByReference_InProgress(@Param("reference") String reference, @Param("uid") String uid, Pageable pageable);

    @Query("SELECT p FROM Package p WHERE p.delivered = true AND p.client.uid = :uid")
    Page<Package> findAll_Delivered(@Param("uid") String uid, Pageable pageable);

    @Query("SELECT p FROM Package p WHERE p.delivered = true AND p.reference LIKE :reference% AND p.client.uid = :uid")
    Page<Package> findByReference_Delivered(@Param("reference") String reference, @Param("uid") String uid, Pageable pageable);
}
