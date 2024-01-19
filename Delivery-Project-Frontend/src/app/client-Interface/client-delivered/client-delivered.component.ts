import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpServiceService } from '../../services/http-service.service';

@Component({
  selector: 'app-client-delivered',
  templateUrl: './client-delivered.component.html',
  styleUrl: './client-delivered.component.css'
})
export class ClientDeliveredComponent implements OnInit {

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
    
  toAccount(): void{

    this.router.navigate(["client-account"])
  }

  toInProgress(): void{

    this.router.navigate(["client-in-progress"])
  }


  ngOnInit(): void {

    if(localStorage.getItem("role") == "CLIENT"){
      this.allowed = true;
    }
  }


}
