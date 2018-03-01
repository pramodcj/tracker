import { Component, OnInit } from '@angular/core';
import {VehicleService} from "../vehicle.service";
import {OrderPipe} from "ngx-order-pipe";

@Component({
  selector: 'app-vehicle-alerts',
  templateUrl: './vehicle-alerts.component.html',
  styleUrls: ['./vehicle-alerts.component.css']
})
export class VehicleAlertsComponent implements OnInit {

  alerts;
  vehicles;
  vin;
  order: string ='vin';
  reverse: boolean = false;
  constructor(private vehicleService: VehicleService, private orderPipe: OrderPipe) { }

  ngOnInit() {
    this.vehicleService.getVehicles()
      .subscribe(
        vehicles => this.vehicles = vehicles,
        error => console.log(error)
      );
  }

  //this.vehicleService.getAlerts(vin);


  getAlerts(){
    var vstatVm = this;
    vstatVm.vehicleService.getAlerts(this.vin).toPromise()
      .then(function (alerts) {
          vstatVm.alerts = alerts;
        },
        function (error){
          console.log(error);
        })
  }

  setOrder(value: string) {
    if (this.order === value) {
      this.reverse = !this.reverse;
    }
    this.order = value;
  }
}
