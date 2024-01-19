import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpServiceService } from '../../services/http-service.service';

@Component({
  selector: 'app-admin-add-user',
  templateUrl: './admin-add-user.component.html',
  styleUrl: './admin-add-user.component.css'
})

export class AdminAddUserComponent implements OnInit {

  page = 0;
  user = 0;
  search = "";

  users: { uid: string, firstname: string, lastname: string, email: string, phonenumber: string, enabled: boolean }[] = [];

  constructor(private router: Router, private httpService: HttpServiceService){}

  ngOnInit(): void {
    const url = "http://localhost:8080/users-api/get-clients-by-name/0/20/all";

    this.httpService.getRequest(url).subscribe(
      (response) => {
        this.users = response.content;
      },
      (error) => {
        console.log(error);
      }
    )
  }

  onUserEnabledChange(uid: string): void {
    var userConfirmed = window.confirm("Are you sure you want to proceed?");
  
    if (userConfirmed) {

      const url = "http://localhost:8080/users-api/enable-or-disable-user/"+uid;

      this.httpService.getRequest(url).subscribe();
    } 
  }


  onUsersChange(): void{
    this.search = "";
    if(this.user == 0){

      const url = "http://localhost:8080/users-api/get-clients-by-name/0/20/all";
      this.httpService.getRequest(url).subscribe(
        (response) => {
          console.log(response.content)
          this.users = response.content;
        },
        (error) => {
          console.log(error);
        }
      )
    }

    else if(this.user == 1){
      
      const url = "http://localhost:8080/users-api/get-checkpoints-by-name/0/20/all";
      this.httpService.getRequest(url).subscribe(
        (response) => {
          console.log(response.content)
          this.users = response.content;
        },
        (error) => {
          console.log(error);
        }
      )
    }

    else if(this.user == 2){
      
      const url = "http://localhost:8080/users-api/get-admins-by-name/0/20/all";
      this.httpService.getRequest(url).subscribe(
        (response) => {
          console.log(response.content)
          this.users = response.content;
        },
        (error) => {
          console.log(error);
        }
      )
    }
  }

  onSearchChange(): void{

    if(this.user == 0){

      if(this.search==""){
        const url = "http://localhost:8080/users-api/get-clients-by-name/0/20/all";
        this.httpService.getRequest(url).subscribe(
          (response) => {
            console.log(response.content)
            this.users = response.content;
          },
          (error) => {
            console.log(error);
          }
        )
      }
      else{
        const url = "http://localhost:8080/users-api/get-clients-by-name/0/20/"+this.search;
        this.httpService.getRequest(url).subscribe(
          (response) => {
            console.log(response.content)
            this.users = response.content;
          },
          (error) => {
            console.log(error);
          }
        )
      }
    }

    
    if(this.user == 1){

      if(this.search==""){
        const url = "http://localhost:8080/users-api/get-checkpoints-by-name/0/20/all";
        this.httpService.getRequest(url).subscribe(
          (response) => {
            console.log(response.content)
            this.users = response.content;
          },
          (error) => {
            console.log(error);
          }
        )
      }
      else{
        const url = "http://localhost:8080/users-api/get-checkpoints-by-name/0/20/"+this.search;
        this.httpService.getRequest(url).subscribe(
          (response) => {
            console.log(response.content)
            this.users = response.content;
          },
          (error) => {
            console.log(error);
          }
        )
      }
    }

    if(this.user == 2){

      if(this.search==""){
        const url = "http://localhost:8080/users-api/get-admins-by-name/0/20/all";
        this.httpService.getRequest(url).subscribe(
          (response) => {
            console.log(response.content)
            this.users = response.content;
          },
          (error) => {
            console.log(error);
          }
        )
      }
      else{
        const url = "http://localhost:8080/users-api/get-admins-by-name/0/20/"+this.search;
        this.httpService.getRequest(url).subscribe(
          (response) => {
            console.log(response.content)
            this.users = response.content;
          },
          (error) => {
            console.log(error);
          }
        )
      }
    }
  }



//      USER CREATION:

  valid = true;

  email: string = '';
  role: string = 'CLIENT';
  firstname: string = '';
  lastname: string = '';
  phone_number: string = '';
  address = "";

  //EMAIL VALIDITY REGULAR EXPRESSION
  expression: RegExp = /^(?=.{1,254}$)(?=.{1,64}@)[-!#$%&'*+/0-9=?A-Z^_`a-z{|}~]+(\.[-!#$%&'*+/0-9=?A-Z^_`a-z{|}~]+)*@[A-Za-z0-9]([A-Za-z0-9-]{0,61}[A-Za-z0-9])?(\.[A-Za-z0-9]([A-Za-z0-9-]{0,61}[A-Za-z0-9])?)*$/;

  onSubmit():void{

    this.valid = this.expression.test(this.email);
    if(this.valid == true && this.firstname.length>2 && this.lastname.length>2 && this.email.length>10 && this.phone_number.length>8){

      const request = {
        "firstname": this.firstname,
        "lastname": this.lastname,
        "role": this.role,
        "email": this.email,
        "phonenumber": this.phone_number,
        "address": this.address
      };
      
      console.log(request);
    
      const url = "http://localhost:8080/authenticationController/register"
  
      this.httpService.postRequest(url, request).subscribe(
        (response) => {
          this.firstname = "";
          this.lastname = "";
          this.email = "";
          this.phone_number = "";
          this.role = "CLIENT";

          alert("User created successfully.")
          this.page = 0;
          this.user = 0;
          this.search = "";

          this.ngOnInit();
        },
        (error) => {
          alert("Email already in use.")
          console.log(error);
        }
      );
    }

    else{
      this.valid = false;
    }
    
  }
}
