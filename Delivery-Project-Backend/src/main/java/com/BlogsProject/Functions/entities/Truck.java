package com.BlogsProject.Functions.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Truck {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String reference;

    String registrationNumber;

    @ManyToOne
    Driver driver;

    @Builder.Default
    boolean free = true;


}
