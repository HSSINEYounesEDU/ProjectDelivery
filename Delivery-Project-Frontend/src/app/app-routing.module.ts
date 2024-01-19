import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminHomepageComponent } from './admin-Interface/admin-homepage/admin-homepage.component';
import { LoginPageComponent } from './authentication/login-page/login-page.component';
import { PasswordChangeComponent } from './authentication/password-change/password-change.component';
import { PasswordRecuperationComponent } from './authentication/password-recuperation/password-recuperation.component';
import { CheckpointDeliveriesComponent } from './checkpoint-Interface/checkpoint-home/checkpoint-deliveries.component';
import { ClientAccountComponent } from './client-Interface/client-account/client-account.component';
import { ClientDeliveredComponent } from './client-Interface/client-delivered/client-delivered.component';
import { ClientInProgressComponent } from './client-Interface/client-in-progress/client-in-progress.component';


const routes: Routes = [
  {path: "", component: LoginPageComponent},
  {path: "login", component: LoginPageComponent},
  {path: "password-recuperation", component: PasswordRecuperationComponent},
  {path: "password-change", component: PasswordChangeComponent},
  


  //            CLIENT LINKS
  {path: "client-in-progress", component: ClientInProgressComponent},
  {path: "client-delivered", component: ClientDeliveredComponent},
  {path: "client-account", component: ClientAccountComponent},

   //            CHECKPOINT LINKS
   {path: "checkpoint-home", component: CheckpointDeliveriesComponent },

  //            ADMIN LINKS
  {path: "admin-home-page", component: AdminHomepageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
