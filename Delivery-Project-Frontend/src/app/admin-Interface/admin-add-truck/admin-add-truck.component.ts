import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpServiceService } from '../../services/http-service.service';

@Component({
  selector: 'app-admin-add-truck',
  templateUrl: './admin-add-truck.component.html',
  styleUrl: './admin-add-truck.component.css'
})
export class AdminAddTruckComponent implements OnInit {

  search = "";
  page = 0;

  drivers: { id: number, firstname: string, lastname: string, email: string, phoneNumber: string, cin: string, enabled: boolean }[] = [];
  trucks: { id: number, reference: string, registrationNumber: string, driver: { id: number, firstname: string, lastname: string, email: string, phoneNumber: string, cin: string, enabled: boolean } }[] = [];
  displayedTrucks: { id: number, reference: string, registrationNumber: string, driver: { id: number, firstname: string, lastname: string, email: string, phoneNumber: string, cin: string, enabled: boolean } }[] = [];

  constructor(private router: Router, private httpService: HttpServiceService) { }

  ngOnInit(): void {

    const url1 = "http://localhost:8080/driver-api/get-enabled-drivers/0/20/all";
    this.httpService.getRequest(url1).subscribe({
      next: (response) => {
        this.drivers = response.content;
      },
      error: (error) => {
        console.error(error);
      }
    });

    const url2 = "http://localhost:8080/truck-api/get-all-trucks/0/20/all";
    this.httpService.getRequest(url2).subscribe({
      next: (response) => {
        this.trucks = response.content;
        this.displayedTrucks = response.content;
      },
      error: (error) => {
        console.error(error);
      }
    });
  }

  //  TRUCK DISPLAY


  onSearchChange(): void {
    if (this.search == "") {
      this.displayedTrucks = this.trucks;
    }
    else {
      this.displayedTrucks = this.trucks.filter(item =>
        item.reference.toLowerCase().startsWith(this.search.toLowerCase())
      );
    }
  }

  option: any;

  onTruckUpdate(truck: { id: number, reference: string, registrationNumber: string, driver: { id: number, firstname: string, lastname: string, email: string, phoneNumber: string, cin: string, enabled: boolean } }): void {

    console.log(truck);
    if (truck.driver == null && this.option != 0) {
      console.log("NULL");
      if (window.confirm("Are you sure you want to proceed?")) {

        const url = "http://localhost:8080/truck-api/update";
        const request = {
          "reference": truck.reference,
          "registrationNumber": truck.registrationNumber,
          "driver": this.option
        };

        console.log(request);

        this.httpService.postRequest(url, request).subscribe({
          next: (response) => {
            this.ngOnInit();
          },
          error: (error) => {
            console.error(error);
          }
        });
        this.ngOnInit();
      }
    }
    else {
      console.log("NOT NULL");
      if (this.option != truck.driver.id) {
        let userConfirmed = window.confirm("Are you sure you want to proceed?");

        if (userConfirmed) {

          const url = "http://localhost:8080/truck-api/update";
          const request = {
            "reference": truck.reference,
            "registrationNumber": truck.registrationNumber,
            "driver": this.option
          };

          this.httpService.postRequest(url, request).subscribe({
            next: (response) => {
              this.ngOnInit();
            },
            error: (error) => {
              console.error(error);
            }
          });
          this.ngOnInit();
        }
      }
    }
  }

  // TRUCK CREATION

  reference = "";
  registrationNumber = "";
  driver = 0;


  onSubmit(): void {

    const request = {
      "reference": this.reference,
      "registrationNumber": this.registrationNumber,
      "driver": this.driver
    }


    if (this.reference.length > 4 && this.registrationNumber.length > 4) {

      const url = "http://localhost:8080/truck-api/create";
      this.httpService.postRequest(url, request).subscribe({
        next: (response) => {
          alert("Truck created successfully.");
          this.page = 0;
          this.reference = "";
          this.registrationNumber = "";
          this.driver = 0;
          this.ngOnInit();
        },
        error: (error) => {
          alert("Truck registration number already exists.");
        }
      });
    }

  }

}
