package pramod.services;


import org.springframework.stereotype.Component;
import pramod.entity.Vehicle;

import java.util.List;

@Component
public interface VehicleService {
    Vehicle createVehicle(Vehicle vehicle);
    Vehicle findOneVehicle(String vinid);
    List<Vehicle> findAllVehicles();
    List<Vehicle> updateVehicle(List<Vehicle> vehicles);
    void deleteVehicle(String vinid);
}
