import { Component, OnInit } from '@angular/core';
import {VehicleService} from "../vehicle.service";
import {ActivatedRoute} from "@angular/router";
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-vehicle-details',
  templateUrl: './vehicle-details.component.html',
  styleUrls: ['./vehicle-details.component.css']
})
export class VehicleDetailsComponent implements OnInit {

  vehicle;
  reading;
  latitude;
  longitude;
  label = {
    color: 'black',
    fontSize: '10px',
    text: ''
  };
  constructor(private route: ActivatedRoute, private vehicleService: VehicleService) { }

  ngOnInit() {
    this.getDetails();
    this.getReading();
  }

  getDetails(){
    this.route.params.subscribe(params => {
      this.vehicleService.getVehicleByVin(params.id)
        .subscribe(
          vehicle => this.vehicle = vehicle,
          error => console.log(error)
        )
    })
  }

  getReading(){
    this.route.params.subscribe(params => {
      this.vehicleService.getVehicleReadingByVin(params.id)
        .subscribe(
          reading => this.reading = reading,
          error => console.log(error)
        )
    })
  }

}
