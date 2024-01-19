package com.BlogsProject.Functions.models;

import com.BlogsProject.Functions.entities.Package;
import com.BlogsProject.Functions.entities.Traject;
import com.BlogsProject.Functions.entities.Truck;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class CargoRequest {
    int id;

    int truckId;

    int trajectId;

    int[] packages;

    Date creationTime;

    Date arrivingTime;
}
