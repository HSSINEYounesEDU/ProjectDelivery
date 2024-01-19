import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-password-recuperation',
  templateUrl: './password-recuperation.component.html',
  styleUrl: './password-recuperation.component.css'
})
export class PasswordRecuperationComponent {

  email: string = '';

  private apiUrl = 'http://localhost:8080/passwordRecoveryController';


  constructor(private http: HttpClient, private router: Router) {}
  

  getData(email: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/send-recovery-mail/`+email);
  }

  onSubmit(): void {  
    if(this.email.length > 10){
      this.getData(this.email).subscribe({
        next: (response) => {
          if (response.status === 200) {
            alert(response.details);
            this.router.navigate(['login']);
          } else if (response.status === 500) {
            alert(response.status + "\n" + response.details);
          }
        },
        error: (error) => {
          console.error(error);
        }
      });
      
    }
  }
}
