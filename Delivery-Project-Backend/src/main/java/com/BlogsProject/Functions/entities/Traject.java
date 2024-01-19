package com.BlogsProject.Functions.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Traject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    City source;

    City destination;

    List<City> road;


}
