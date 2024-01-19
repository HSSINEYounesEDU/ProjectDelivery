package com.BlogsProject.Functions.services;

import com.BlogsProject.Functions.entities.Traject;
import com.BlogsProject.Functions.repositories.TrajectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TrajectService {

    private final TrajectRepository trajectRepository;

    public void create(Traject traject){
        trajectRepository.save(traject);
    }

    public Page<Traject> findTrajects(int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("source").ascending());
        return trajectRepository.findAll(pageable);
    }

}
