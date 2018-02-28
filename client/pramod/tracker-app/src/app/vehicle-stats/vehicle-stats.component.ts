import { Component, OnInit } from '@angular/core';
import {VehicleService} from "../vehicle.service";
import {forEach} from "@angular/router/src/utils/collection";

interface svalue {
  name: string;
  value: number;
}

interface values{
  name: string;
  series: Array<svalue>;
}

@Component({
  selector: 'app-vehicle-stats',
  templateUrl: './vehicle-stats.component.html',
  styleUrls: ['./vehicle-stats.component.css']
})

export class VehicleStatsComponent implements OnInit {

  vehicles;
  from;
  to;
  prop = null;
  vin;
  readings;
  showMap;

  val = {
    "name": "",
    "value": ""
  };

  chartdata=[{
    "name": "",
    "series": []
  }];

  view = [700, 400];
  // options
  showXAxis = true;
  showYAxis = true;
  gradient = false;
  showLegend = true;
  showXAxisLabel = true;
  xAxisLabel = 'Time';
  showYAxisLabel = true;
  yAxisLabel = this.prop;
  updateInterval;


  colorScheme = {
    domain: ['#5AA454', '#A10A28', '#C7B42C', '#AAAAAA']
  };

  // line, area
  autoScale = true;

  constructor(private vehicleService: VehicleService ) { }

  ngOnInit() {
    this.vehicleService.getVehicles()
      .subscribe(
        vehicles => this.vehicles = vehicles,
        error => console.log(error)
      );
    //this.updateInterval = setInterval(() => this.refreshData(), 1000);
  }


refreshData(){
    this.chartdata = [...this.chartdata];
}


  getList(){

    this.vehicleService.getList(this.vin,this.prop,this.from,this.to)
      .subscribe(
        (readings) => {
          this.readings = readings;
          this.chartdata[0].name = this.prop;
          for(let reading of this.readings){
            this.val.name=reading.timestamp;
            this.val.value=reading[this.prop];
            this.chartdata[0].series.push(this.val);
            this.chartdata = [...this.chartdata];
          }

        },
            error => console.log(error)
      );
    /*this.chartdata.name = this.prop;
    console.log(this.chartdata.name);
    console.log(this.readings);
    for(let reading of this.readings){
      this.val.name=reading.timestamp;
      this.val.value=reading[this.prop];
      this.chartdata.series.push(this.val);
    }
    console.log(this.chartdata);*/

  }

  reset(){
    this.from="";
    this.to="";
    this.prop="";
    this.vin="";
  }

}
