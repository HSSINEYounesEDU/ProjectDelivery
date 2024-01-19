package com.BlogsProject.Functions.repositories;

import com.BlogsProject.Functions.entities.Traject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrajectRepository extends JpaRepository<Traject, Integer> {

}
