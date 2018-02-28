import { Component, OnInit } from '@angular/core';
import {VehicleService} from "../vehicle.service";

@Component({
  selector: 'app-vehicle-alerts',
  templateUrl: './vehicle-alerts.component.html',
  styleUrls: ['./vehicle-alerts.component.css']
})
export class VehicleAlertsComponent implements OnInit {

  alerts;
  constructor(private vehicleService: VehicleService) { }

  ngOnInit() {
    this.vehicleService.getAlerts()
      .subscribe(
        alerts => this.alerts = alerts,
        error => console.log(error)
      );
  }

}
