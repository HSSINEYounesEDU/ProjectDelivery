import { Component, OnInit } from '@angular/core';
import {  Router } from '@angular/router';

@Component({
  selector: 'app-admin-homepage',
  templateUrl: './admin-homepage.component.html',
  styleUrl: './admin-homepage.component.css'
})
export class AdminHomepageComponent implements OnInit {

  allowed = true;
  page = 0;

  constructor(private router: Router){}

  ngOnInit(): void {
    if(localStorage.getItem("role") == "ADMIN"){
      this.allowed = true;
    }
  }

  filter = "client";

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

  
}
