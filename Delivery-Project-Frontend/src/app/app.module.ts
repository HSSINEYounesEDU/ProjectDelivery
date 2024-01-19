import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { PasswordRecuperationComponent } from './authentication/password-recuperation/password-recuperation.component';
import { PasswordChangeComponent } from './authentication/password-change/password-change.component';
import { LoginPageComponent } from './authentication/login-page/login-page.component';
import { ClientFooterComponent } from './client-Interface/client-footer/client-footer.component';
import { ClientInProgressComponent } from './client-Interface/client-in-progress/client-in-progress.component';
import { AdminHomepageComponent } from './admin-Interface/admin-homepage/admin-homepage.component';
import { AdminAddUserComponent } from './admin-Interface/admin-add-user/admin-add-user.component';
import { ClientDeliveredComponent } from './client-Interface/client-delivered/client-delivered.component';
import { ClientAccountComponent } from './client-Interface/client-account/client-account.component';
import { CheckpointDeliveriesComponent } from './checkpoint-Interface/checkpoint-home/checkpoint-deliveries.component';
import { CheckpointInProgressComponent } from './checkpoint-Interface/checkpoint-in-progress/checkpoint-in-progress.component';
import { CheckpointDeliveredComponent } from './checkpoint-Interface/checkpoint-delivered/checkpoint-delivered.component';
import { AdminAddDriverComponent } from './admin-Interface/admin-add-driver/admin-add-driver.component';
import { AdminAddTruckComponent } from './admin-Interface/admin-add-truck/admin-add-truck.component';
import { AdminAddTrajectComponent } from './admin-Interface/admin-add-traject/admin-add-traject.component';
import { CheckpointSendCargoComponent } from './checkpoint-Interface/checkpoint-send-cargo/checkpoint-send-cargo.component';
import { CheckpointReceiveCargoComponent } from './checkpoint-Interface/checkpoint-receive-cargo/checkpoint-receive-cargo.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    PasswordRecuperationComponent,
    PasswordChangeComponent,
    ClientFooterComponent,
    ClientInProgressComponent,
    AdminHomepageComponent,
    AdminAddUserComponent,
    ClientDeliveredComponent,
    ClientAccountComponent,
    CheckpointDeliveriesComponent,
    CheckpointInProgressComponent,
    CheckpointDeliveredComponent,
    AdminAddDriverComponent,
    AdminAddTruckComponent,
    AdminAddTrajectComponent,
    CheckpointSendCargoComponent,
    CheckpointReceiveCargoComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
