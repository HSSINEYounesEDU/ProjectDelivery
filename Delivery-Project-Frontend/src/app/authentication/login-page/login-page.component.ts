import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { OnInit } from '@angular/core';


@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.css'
})
export class LoginPageComponent implements OnInit{

  loggedIn = true;

  email: string = '';
  password: string = '';

  private apiUrl = 'http://localhost:8080/authenticationController';

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    if(localStorage.getItem("activeToken")==null){
      this.loggedIn = false;
    }
  }
  

  postData(request: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/authenticate`, request);
  }

  onLogin(): void {
    const request = {
      "email": this.email,
      "password": this.password
    };
  
    if(this.email.length>10 && this.password.length>5){
      this.postData(request).subscribe({
        next: (response) => {
          // Handle the response from the server
          const jsonResponse = JSON.stringify(response, null, 2);
          localStorage.setItem("activeToken", response.activeToken)
          localStorage.setItem("uid", response.uid)
          localStorage.setItem("firstname", response.firstname)
          localStorage.setItem("lastname", response.lastname)
          localStorage.setItem("email", response.email)
          localStorage.setItem("phonenumber", response.phonenumber)
          localStorage.setItem("role", response.role)
            
          if(response.role == "CLIENT"){
            this.router.navigate(['client-in-progress']);
          }
  
          else if(response.role == "ADMIN"){
            this.router.navigate(['admin-home-page']);
          }
  
          else if(response.role == "CHECKPOINT"){
            this.router.navigate(['checkpoint-home']);
          }
  
          
        },
        error: (error) => {
          // Handle errors from the server
          alert('Login Failed !');
          console.error(error);
        }
      });
    }
    
  }

  onSubmit(): void{
    this.router.navigate(["sign-up"])
  }
}
