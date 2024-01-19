import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpServiceService } from '../../services/http-service.service';

@Component({
  selector: 'app-checkpoint-send-cargo',
  templateUrl: './checkpoint-send-cargo.component.html',
  styleUrl: './checkpoint-send-cargo.component.css'
})
export class CheckpointSendCargoComponent {

  currentLocation = "";
  destination = "";

  trajects: {id: number, source: string, destination: string}[] = [];


  packages: {id: number, reference: string, description: string, presentLocation: string, weight: number, address: string, traject:{id: number, source: string, destination: string, road: string[]}, client:{uid: string, firstname: string, lastname: string, email: string, phonenumber: string}}[] = [];
  Allpackages: {id: number, reference: string, description: string, presentLocation: string, weight: number, address: string, traject:{id: number, source: string, destination: string, road: string[]}, client:{uid: string, firstname: string, lastname: string, email: string, phonenumber: string}}[] = [];
  trucks: {id: number, reference: string, registrationNumber: string}[] = [];


  constructor(private httpService: HttpServiceService, private router: Router){}
  

  responseFunction(url: string):void{
    this.httpService.getRequest(url).subscribe({
      next: (response) => {
        this.packages = response.content;
        this.Allpackages = response.content;
      },
      error: (error) => {
        console.error(error);
      }
    });
  }
  ngOnInit(): void {
    const url1 = "http://localhost:8080/package-api/get-in-chekpoints-packages/0/20/all"
    this.responseFunction(url1);

    const url2 = "http://localhost:8080/traject-api/get-trajects/0/20"
    this.responseFunction(url2);

    const url3 = "http://localhost:8080/truck-api/get-available-trucks/0/20/all"
    this.responseFunction(url3);
  };

  onSourceSearchChange(): void{
    if(this.currentLocation=="" && this.destination == ""){
      this.packages = this.Allpackages;
    }
    else {
      this.packages = this.Allpackages.filter(item =>
      item.presentLocation.toLowerCase().startsWith(this.currentLocation.toLowerCase()));
    }
  }

  truckId = "";
  trajectId = "";
  packs: number[] = [];

  onSubmit():void{
    const request = {
        "id": 0,
        "truckId": this.truckId,
        "trajectId": this.trajectId,
        "packages": this.packs,
        "creationTime": null,
        "arrivingTime": null
    }
  
    if(this.packs.length>0 && this.trajectId!="" && this.truckId!=""){
      const url = "http://localhost:8080/cargo-api/create";
      this.httpService.postRequest(url, request).subscribe({
        next: (response) =>{
          alert("Cargo created successfully.")
          this.truckId = "";
          this.trajectId = "";
          this.ngOnInit()
        },
        error: (error)=>{
          console.log(error);
        }
      }
      );
        
    }
    else if(this.packs.length==0){
      alert("Empty cargo.")
    }
    else if(this.truckId == ""){
      alert("Indicate the truck for the cargo.")
    }
    else if(this.trajectId == ""){
      alert("Indicate the traject for the cargo.")
    }
  }


  onCheckboxChange(event: any, id: number) {
    if (event.target.checked) {
      this.packs.push(id);
    } else {
      this.packs = this.packs.filter(item => item !== id);
    }
  }

}
