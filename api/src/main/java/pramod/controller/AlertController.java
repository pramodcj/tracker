package pramod.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pramod.entity.Alert;
import pramod.entity.Vehicle;
import pramod.entity.VehicleStatistics;
import pramod.exception.BadRequestException;
import pramod.exception.ResourceNotFoundException;
import pramod.services.AlertService;
import pramod.services.VehicleService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","http://mocker.egen.io"},maxAge = 3600)
@RestController
@RequestMapping("/alerts")
public class AlertController {
    @Autowired
    AlertService alertService;

    @Autowired
    VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.GET,value = "/HIGH/two_hours/all")
    public List<VehicleStatistics> findAllalerts(){
        List<VehicleStatistics> stats = alertService.getAllAlerts();
        //List<VehicleStatistics> vehicleStatistics = alertService.getAllAlerts();
        /*List<VehicleStatistics> vehicleStatistics = new ArrayList<VehicleStatistics>();
        for (VehicleStatistics alert:alerts)
            vehicleStatistics.add(new VehicleStatistics(alert.getVin(),alert.getCount()));
        return vehicleStatistics;*/
        return stats;
    }

    @RequestMapping(method = RequestMethod.GET,value = "/all/{vin}")
    public List<Alert> findAllAlertsByVin(@PathVariable("vin")String vin){
        Vehicle vehicle = vehicleService.findOneVehicle(vin);
        if(vehicle!=null) {
            List<Alert> alerts = alertService.getAllAlertsByVin(vin);
            if (alerts != null)
                return alerts;
            else
                throw new ResourceNotFoundException("There are no alerts for this vehicle");
        }
        else
            throw new BadRequestException("No Vehicle Exists with Given vin#" + vin);
    }
}
