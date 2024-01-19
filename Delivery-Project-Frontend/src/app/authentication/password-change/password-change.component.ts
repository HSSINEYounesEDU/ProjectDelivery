import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-password-change',
  templateUrl: './password-change.component.html',
  styleUrl: './password-change.component.css'
})
export class PasswordChangeComponent {

  password1: string = "";
  password2: string = "";
  token: any = "";

  constructor(private http: HttpClient, private router: Router, private route: ActivatedRoute) {
    this.route.queryParams.subscribe(params => {
      this.token = params['token'];
    });
  }
  
  private apiUrl = 'http://localhost:8080/passwordRecoveryController';

  postData(request: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/change-account-password`, request);
  }

  expression: RegExp =  /^(?=[^A-Z]*[A-Z])(?=[^a-z]*[a-z])(?=\D*\d).{8,}$/;

  onSubmit(): void {  
    
    const request = {
      "token": this.token,
      "password": this.password1
    };
    
    const valid: boolean = this.expression.test(this.password1);    //TESTING EMAIL FORM VALIDITY

    alert("Token: "+request.token +"\n Password: "+request.password);

    if(this.password1===this.password2 && valid){

      this.postData(request).subscribe({
        next: (response) => {
          if (response.status === 200 || response.status === 500) {
            alert(response.details);
          } 
        },
        error: (error) => {
          alert('Token expired.');
          console.error(error);
        }
      });
      
    }
    else if(this.password1 !== this.password2){
      alert("Passwords do not match.")
    }
    else if(!valid){
      alert("Weak password.")
    }
    
  }

}
