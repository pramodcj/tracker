import {Component, OnInit} from '@angular/core';
import {VehicleService} from "../vehicle.service";
import {OrderPipe} from "ngx-order-pipe";

@Component({
  selector: 'app-vehicle-highalerts',
  templateUrl: './vehicle-highalerts.component.html',
  styleUrls: ['./vehicle-highalerts.component.css']
})
export class VehicleHighAlertsComponent implements OnInit {

  highalerts;
  order: string ='vin';
  reverse: boolean = false;

  constructor(private vehicleService: VehicleService, private orderPipe: OrderPipe) {
  }

  ngOnInit() {
    this.vehicleService.getHighAlerts()
      .subscribe(
        highalerts => this.highalerts = highalerts,
        error => console.log(error)
      );
  }



  setOrder(value: string) {
    if (this.order === value) {
      this.reverse = !this.reverse;
    }
    this.order = value;
  }

}
