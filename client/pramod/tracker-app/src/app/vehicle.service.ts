import { Injectable } from '@angular/core';
import {Observable} from "rxjs/Observable";
import {HttpClient, HttpParams} from "@angular/common/http";

import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';
import 'rxjs/add/observable/throw';


@Injectable()
export class VehicleService {

  constructor(private http: HttpClient) { }

  getVehicles(){
    return this.http.get('http://localhost:8080/cartracker/vehicles');
  }
  getVehicleByVin(vin){
    return this.http.get(`http://localhost:8080/cartracker/vehicles/${vin}`);
  }
  getVehiclesLocation(){
    return this.http.get('http://localhost:8080/cartracker/readings/all_location');
  }

  getVehicleReadingByVin(vin){
    return this.http.get(`http://localhost:8080/cartracker/readings/${vin}`);
  }

  getHighAlerts(){
    return this.http.get('http://localhost:8080/cartracker/alerts/HIGH/two_hours/all');
  }

  getAlerts(vin){
    return this.http.get(`http://localhost:8080/cartracker/alerts/all/${vin}`);
  }

  getList(vin,prop,from,to){

    let prms = new HttpParams();
    prms = prms.append('vin',vin);
    prms = prms.append('prop',prop);
    prms = prms.append('from',from);
    prms = prms.append('to',to);
    console.log(prms);

    return this.http.get(`http://localhost:8080/cartracker/readings/find`,{params: prms});
  }

}
