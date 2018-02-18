package pramod.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pramod.entity.Vehicle;
import pramod.exception.BadRequestException;
import pramod.exception.ResourceNotFoundException;
import pramod.repository.VehicleRepository;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    VehicleRepository vehicleRepository;

    @Transactional
    public Vehicle createVehicle(Vehicle vehicle){
        Vehicle exist = vehicleRepository.findOneVehicle(vehicle.getVin());
        if(exist == null)
            return vehicleRepository.createVehicle(vehicle);
        else
            throw new BadRequestException("Vehicle with vin#" + vehicle.getVin() +" already present");
    }

    public Vehicle findOneVehicle(String vinid) {
        Vehicle exist = vehicleRepository.findOneVehicle(vinid);
        if(exist != null)
            return exist;
        else
            throw new ResourceNotFoundException("Vehicle with vin#" + vinid +" does not exist");
    }

    public List<Vehicle> findAllVehicles(){
        List<Vehicle> vehicles = vehicleRepository.findAllVehicles();
        if(vehicles.size()!=0)
            return vehicles;
        else
            throw new ResourceNotFoundException("No Vehicle is Available");
    }

    @Transactional
    public List<Vehicle> updateVehicle(List<Vehicle> vehicles) {
        for(Vehicle vehicle  : vehicles)
        {
            Vehicle exist = vehicleRepository.findOneVehicle(vehicle.getVin());
            if(exist == null)
                vehicleRepository.createVehicle(vehicle);
            else
                vehicleRepository.updateVehicle(vehicle);
        }
        return vehicles;
    }

    @Transactional
    public void deleteVehicle(String vinid){
        Vehicle exist = vehicleRepository.findOneVehicle(vinid);
        if(exist == null)
            throw new BadRequestException("No Vehicle is Present with given vin#" + vinid);
        else
            vehicleRepository.deleteVehicle(exist);
    }

}
