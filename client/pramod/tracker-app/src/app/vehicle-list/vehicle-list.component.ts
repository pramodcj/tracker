import { Component, OnInit } from '@angular/core';
import {VehicleService} from "../vehicle.service";
import {error} from "util";
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-vehicle-list',
  templateUrl: './vehicle-list.component.html',
  styleUrls: ['./vehicle-list.component.css']
})
export class VehicleListComponent implements OnInit {

  vehicles;
  readings;
  label = {
    color: 'black',
    fontSize: '10px',
    text: ''
  };

  constructor(private vehicleService: VehicleService) { }

  ngOnInit() {
    this.vehicleService.getVehicles()
      .subscribe(
        vehicles => this.vehicles = vehicles,
        error => console.log(error)
      );
    this.vehicleService.getVehiclesLocation()
      .subscribe(
        readings => this.readings = readings,
        error => console.log(error)
      );
  }

}
