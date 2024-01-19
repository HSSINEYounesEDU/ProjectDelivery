package com.BlogsProject.Functions.models;

import com.BlogsProject.Functions.entities.Driver;
import com.BlogsProject.Functions.entities.Truck;
import com.BlogsProject.Functions.services.DriverService;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TruckRequest {

    String reference;

    String registrationNumber;

    int driver;

}
