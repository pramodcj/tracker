package pramod.repository;

import pramod.entity.Vehicle;

import java.util.List;

public interface VehicleRepository {

    Vehicle createVehicle(Vehicle vehicle);
    Vehicle findOneVehicle(String vinid);
    List<Vehicle> findAllVehicles();
    Vehicle updateVehicle(Vehicle vehicle);
    void deleteVehicle(Vehicle vehicle);
}
