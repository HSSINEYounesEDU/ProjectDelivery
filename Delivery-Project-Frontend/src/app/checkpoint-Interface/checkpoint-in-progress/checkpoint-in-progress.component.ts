import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpServiceService } from '../../services/http-service.service';

@Component({
  selector: 'app-checkpoint-in-progress',
  templateUrl: './checkpoint-in-progress.component.html',
  styleUrl: './checkpoint-in-progress.component.css'
})
export class CheckpointInProgressComponent implements OnInit{

  valid = true;
  page = 0;
  search = "";

  trajects: {id: string, source: string, destination: string}[] = [];


  packages: {id: number, reference: string, description: string, presentLocation: string, weight: number, address: string, traject:{id: number, source: string, destination: string}, client:{uid: string, firstname: string, lastname: string, email: string, phonenumber: string}}[] = [];
  Allpackages: {id: number, reference: string, description: string, presentLocation: string, weight: number, address: string, traject:{id: number, source: string, destination: string}, client:{uid: string, firstname: string, lastname: string, email: string, phonenumber: string}}[] = [];

  constructor(private httpService: HttpServiceService, private router: Router){}
  
  ngOnInit(): void {
    const url1 = "http://localhost:8080/package-api/get-in-progress-packages/0/20/all"
    this.httpService.getRequest(url1).subscribe({
      next: (response) => {
        this.packages = response.content;
        this.Allpackages = response.content;
      },
      error: (error) => {
        console.error(error);
      }
    });

    const url2 = "http://localhost:8080/traject-api/get-trajects/0/20"
    this.httpService.getRequest(url2).subscribe({
      next: (response) => {
        this.trajects = response.content;
      },
      error: (error) => {
        console.error(error);
      }
    });
    
  };

  onSearchChange(): void{
    this.packages = this.Allpackages.filter(item =>
      item.reference.toLowerCase().startsWith(this.search.toLowerCase())
    );
  }


  email = "";
  weight = "";
  description = "";
  address = "";
  trajectId = "";
  

  onSubmit(): void{

    const url =  "http://localhost:8080/package-api/create";

    if(this.email.length>10 && this.description.length>10 && this.address.length>5 && this.weight!=""){
      this.valid = true;
      const request = {
        "id": 0,
        "reference": "",
        "description": this.description,
        "weight": this.weight,
        "delivered": false,
        "address": this.address,
        "email": this.email,
        "trajectId": this.trajectId,
        "state": "ONCHECKPOINT"
        
      };

      console.log(request)
      
      this.httpService.postRequest(url, request).subscribe({
        next: (response) => {
          alert("Package created successfully.");
      
          this.description = "";
          this.weight = "";
          this.email = "";
          this.address = "";
          this.trajectId = "";
      
          this.page = 0;
      
          this.ngOnInit();
        },
        error: (error) => {
          const userConfirmed = window.confirm("This is either an employee account or a non existing one. \nDo you wanna create it?");
          if (userConfirmed) {
            this.page = 2;
          }
        }
      });
      
    }
    else{this.valid = false}
  }


  //      USER CREATION:

  valid2 = true;

  role: string = 'CLIENT';
  firstname: string = '';
  lastname: string = '';
  phone_number: string = '';
  address2 = "";

  //EMAIL VALIDITY REGULAR EXPRESSION
  expression: RegExp = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  onCreate():void{

    this.valid = this.expression.test(this.email);
    if(this.valid == true && this.firstname.length>2 && this.lastname.length>2 && this.email.length>10 && this.phone_number.length>8){

      const request = {
        "firstname": this.firstname,
        "lastname": this.lastname,
        "role": this.role,
        "email": this.email,
        "phonenumber": this.phone_number,
        "address": this.address2
      };
      
      console.log(request);
    
      const url = "http://localhost:8080/authenticationController/register"
  
      this.httpService.postRequest(url, request).subscribe({
        next: (response) => {
          this.firstname = "";
          this.lastname = "";
          this.phone_number = "";
      
          alert("User created successfully.\nNow you can create a delivery with this email.");
          this.page = 1;
        },
        error: (error) => {
          alert("Email already in use.");
          console.error(error);
        }
      });
      
    }

    else{
      this.valid = false;
    }
    
  }
}
