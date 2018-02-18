package pramod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pramod.entity.Vehicle;
import pramod.services.VehicleService;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","http://mocker.egen.io"},maxAge = 3600)
@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    VehicleService vehicleService;

    @RequestMapping(method = RequestMethod.POST)
    public Vehicle createVehicle(@RequestBody Vehicle vehicle){
        return vehicleService.createVehicle(vehicle);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{vinid}")
    public Vehicle findOneVehicle(@PathVariable("vinid")String vinid){
        return vehicleService.findOneVehicle(vinid);
    }

    @RequestMapping(method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Vehicle> findAllVehicles(){
        return vehicleService.findAllVehicles();
    }

    @RequestMapping(method = RequestMethod.PUT)
    public List<Vehicle> updateVehicle(@RequestBody List<Vehicle> vehicles){
        return vehicleService.updateVehicle(vehicles);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{vinid}")
    public void deleteVehicle(@PathVariable("vinid")String vinid){
        vehicleService.deleteVehicle(vinid);
    }
}
