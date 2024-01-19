package com.BlogsProject.Functions.controllers;

import com.BlogsProject.Functions.entities.Traject;
import com.BlogsProject.Functions.services.TrajectService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/traject-api")
@RequiredArgsConstructor
public class TrajectController {

    private final TrajectService trajectService;

    @PostMapping("/create")
    public void create(@RequestBody Traject traject){
        trajectService.create(traject);
    }

    @GetMapping ("/get-trajects/{page}/{pageSize}")
    public Page<Traject> create(@PathVariable("page") int page, @PathVariable("pageSize") int pageSize){
        return trajectService.findTrajects(page, pageSize);
    }
}
