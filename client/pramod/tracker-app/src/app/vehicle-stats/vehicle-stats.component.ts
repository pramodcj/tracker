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

  constructor(private vehicleService: VehicleService) { }

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

     var vstatVm = this;

    vstatVm.vehicleService.getList(this.vin,this.prop,this.from,this.to).toPromise()
      .then(function (readings) {
        vstatVm.readings = readings;

        vstatVm.chartdata[0].name = vstatVm.prop;
        for (let reading of vstatVm.readings) {
          const val = {
            "name": reading.timestamp,
            "value": reading[vstatVm.prop]
          }
          vstatVm.chartdata[0].series.push(val);
          vstatVm.chartdata = [...vstatVm.chartdata];

        }
        console.log(vstatVm.chartdata);
      },
        function (error){
          console.log(error);
        })
  }

  reset(){
    this.from="";
    this.to="";
    this.prop="";
    this.vin="";
  }

}
