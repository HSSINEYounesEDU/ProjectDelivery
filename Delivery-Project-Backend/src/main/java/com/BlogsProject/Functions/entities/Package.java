package com.BlogsProject.Functions.entities;


import com.BlogsProject.Authentication.Entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    String reference;

    String description;

    @ManyToOne
    Traject traject;

    City presentLocation;

    String address;

    float weight;

    boolean delivered;

    @ManyToOne
    User client;

    @Builder.Default
    State state = State.ONCHECKPOINT;

    Date creationDate;

    Date deliveryDate;

}
