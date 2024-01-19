package com.BlogsProject.Authentication.Entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByEmail(String email);

    @Query("SELECT U FROM userAccount U WHERE (U.firstname ILIKE :debut%) OR (U.lastname ILIKE :debut%)")
    Page<User> findUserByNameDebut(@Param("debut") String debut, Pageable pageable);

    @Query("SELECT U FROM userAccount U WHERE ((U.firstname ILIKE :debut%) OR (U.lastname ILIKE :debut%)) AND U.role = :role")
    Page<User> findUsersByName(@Param("debut") String debut, Pageable pageable, Role role);

    @Query("SELECT U FROM userAccount U WHERE U.role = :role")
    Page<User> findAllUsers(Pageable pageable, Role role);

}
