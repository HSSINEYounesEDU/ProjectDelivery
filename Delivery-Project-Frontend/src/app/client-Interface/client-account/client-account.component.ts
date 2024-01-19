import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpServiceService } from '../../services/http-service.service';

@Component({
  selector: 'app-client-account',
  templateUrl: './client-account.component.html',
  styleUrl: './client-account.component.css'
})
export class ClientAccountComponent implements OnInit {

  allowed = false;

  constructor(private httpService: HttpServiceService, private router: Router){}

  onLogout():void {

    localStorage.removeItem("activeToken");
    localStorage.removeItem("uid");
    localStorage.removeItem("firstname");
    localStorage.removeItem("lastname");
    localStorage.removeItem("email");
    localStorage.removeItem("phonenumber");
    localStorage.removeItem("role");

    this.router.navigate(["login"]);
  }
    
  toDelivered(): void{

    this.router.navigate(["client-delivered"])
  }

  toInProgress(): void{

    this.router.navigate(["client-in-progress"])
  }


  ngOnInit(): void {

    if(localStorage.getItem("role") ==  "CLIENT"){
      this.allowed = true;
    }
  }
}
