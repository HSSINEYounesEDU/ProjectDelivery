import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-checkpoint-deliveries',
  templateUrl: './checkpoint-deliveries.component.html',
  styleUrl: './checkpoint-deliveries.component.css'
})
export class CheckpointDeliveriesComponent implements OnInit {

  allowed = false;

  page = "0";

  constructor(private router: Router){}

  ngOnInit(): void {
    if(localStorage.getItem("role") == "CHECKPOINT"){
      this.allowed = true;
    }
  }

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
