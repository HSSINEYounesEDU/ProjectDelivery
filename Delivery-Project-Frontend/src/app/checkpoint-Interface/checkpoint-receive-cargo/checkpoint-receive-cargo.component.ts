import { Component, OnInit } from '@angular/core';
import { HttpServiceService } from '../../services/http-service.service';

@Component({
  selector: 'app-checkpoint-receive-cargo',
  templateUrl: './checkpoint-receive-cargo.component.html',
  styleUrl: './checkpoint-receive-cargo.component.css'
})
export class CheckpointReceiveCargoComponent implements OnInit{

  truckId = "";

  trucks: {id: number, reference: string, registrationNumber: string}[] = [];

  cargo: {id: number, packages: {id: number, weight: number, description: string, client: {email: string, phonenumber: string}}[] } = {
    id: 0,
    packages: [{
      id:0,
      description: "",
      weight: 0,
      client: {
        email: "",
        phonenumber: ""
      }
    }],
  };

  constructor(private httpService: HttpServiceService){}

  ngOnInit(): void {
    const url1 = "http://localhost:8080/truck-api/get-active-trucks/0/20/all"
    this.httpService.getRequest(url1).subscribe({
      next: (response) => {
        this.trucks = response.content;
      },
      error: (error) => {
        console.error(error);
      }
    });
    
  }

  onTruckChange(): void{
    const url2 = "http://localhost:8080/cargo-api/get-cargo-by-truck-id/"+this.truckId;
    this.httpService.getRequest(url2).subscribe({
      next: (response) => {
        this.trucks = response.content;
      },
      error: (error) => {
        console.error(error);
      }
    });
    
  }


  onReceive(): void{
    const url3 = "http://localhost:8080/cargo-api/receive/"+this.cargo.id;
    this.httpService.getRequest(url3).subscribe({
      next: (response) => {
        alert("Cargo has been received.");
        this.truckId = "";
        this.cargo = {
          id: 0,
          packages: [{
            id: 0,
            description: "",
            weight: 0,
            client: {
              email: "",
              phonenumber: ""
            }
          }],
        };
      this.ngOnInit();
      },
      error: (error) => {
        console.error(error);
      }
    });
    
  }
  

}
