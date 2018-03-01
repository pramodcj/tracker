import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { VehicleListComponent } from './vehicle-list/vehicle-list.component';
import { VehicleDetailsComponent } from './vehicle-details/vehicle-details.component';
import { VehicleHighAlertsComponent } from './vehicle-highalerts/vehicle-highalerts.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {RouterModule, Routes} from "@angular/router";
import {VehicleService} from "./vehicle.service";
import {AgmCoreModule} from "@agm/core";
import {NgxChartsModule} from "@swimlane/ngx-charts";
import { VehicleStatsComponent } from './vehicle-stats/vehicle-stats.component';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {OrderModule} from "ngx-order-pipe";
import { VehicleAlertsComponent } from './vehicle-alerts/vehicle-alerts.component';

const appRoutes: Routes = [
  { path: 'vehicles', component: VehicleListComponent },
  { path: 'vehicles/:id', component: VehicleDetailsComponent },
  { path: 'highAlerts', component: VehicleHighAlertsComponent },
  { path: 'vehicleStats', component: VehicleStatsComponent },
  { path: 'alerts', component: VehicleAlertsComponent },
  { path: '', redirectTo: '/vehicles', pathMatch: 'full' }
];

@NgModule({
  declarations: [
    AppComponent,
    VehicleListComponent,
    VehicleDetailsComponent,
    VehicleHighAlertsComponent,
    VehicleStatsComponent,
    VehicleAlertsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes),
    AgmCoreModule.forRoot({apiKey: 'AIzaSyCQne3cOrAY1AMN9nx9Pm_k33GQdJHMKQ4'}),
    NgxChartsModule,
    BrowserAnimationsModule,
    OrderModule
  ],
  providers: [VehicleService],
  bootstrap: [AppComponent]
})
export class AppModule { }
