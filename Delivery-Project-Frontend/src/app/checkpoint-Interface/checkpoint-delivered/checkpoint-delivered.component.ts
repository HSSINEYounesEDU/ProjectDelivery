import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpServiceService } from '../../services/http-service.service';

@Component({
  selector: 'app-checkpoint-delivered',
  templateUrl: './checkpoint-delivered.component.html',
  styleUrl: './checkpoint-delivered.component.css'
})
export class CheckpointDeliveredComponent {

  search = "";

  trajects: { id: string, source: string, destination: string }[] = [];


  packages: { id: number, reference: string, description: string, weight: number, address: string, traject: { id: number, source: string, destination: string }, client: { uid: string, firstname: string, lastname: string, email: string, phonenumber: string } }[] = [];
  Allpackages: { id: number, reference: string, description: string, weight: number, address: string, traject: { id: number, source: string, destination: string }, client: { uid: string, firstname: string, lastname: string, email: string, phonenumber: string } }[] = [];

  constructor(private httpService: HttpServiceService, private router: Router) { }

  ngOnInit(): void {
    const url = "http://localhost:8080/traject-api/"

    this.httpService.getRequest(url + "get-delevired-packages/0/20/all").subscribe({
      next: (response) => {
        this.packages = response.content;
        this.Allpackages = response.content;
        console.log(response);
      },
      error: (error) => {
        console.error(error);
      }
    });

    this.httpService.getRequest(url + "get-trajects/0/20").subscribe({
      next: (response) => {
        this.trajects = response.content;
      },
      error: (error) => {
        console.error(error);
      }
    });

  };

  onSearchChange(): void {
    this.packages = this.Allpackages.filter(item =>
      item.reference.toLowerCase().startsWith(this.search.toLowerCase())
    );
  }
}
