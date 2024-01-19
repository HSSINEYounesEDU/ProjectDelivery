package com.BlogsProject.Functions.models;

import com.BlogsProject.Authentication.Entity.User;
import com.BlogsProject.Functions.entities.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PackageRequest {

    int id;

    String reference;

    String description;

    String address;

    float weight;

    boolean delivered = false;

    String email;

    int trajectId;

    City presentLocation;
}
