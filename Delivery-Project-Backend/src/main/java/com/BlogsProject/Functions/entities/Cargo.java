package com.BlogsProject.Functions.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cargo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @ManyToOne
    Truck truck;

    @ManyToOne
    Traject traject;

    @ManyToMany
    List<Package> packages;

    Date creationTime;

    Date arrivingTime;

}
