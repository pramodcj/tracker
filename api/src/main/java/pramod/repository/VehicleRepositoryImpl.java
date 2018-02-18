package pramod.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pramod.entity.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class VehicleRepositoryImpl implements VehicleRepository {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    VehicleRepository vehicleRepository;

    public Vehicle createVehicle(Vehicle vehicle) {
        System.out.println("Inside Repository");
        em.persist(vehicle);
        return vehicle;
    }

    public Vehicle findOneVehicle(String vinid) {

        TypedQuery<Vehicle> query = em.createNamedQuery("Vehicle.findone", Vehicle.class);
        query.setParameter("paramVin", vinid);

        List<Vehicle> listvehicles = query.getResultList();

        if(listvehicles.size()==1)
            return listvehicles.get(0);
        else
            return null;
    }

    public List<Vehicle> findAllVehicles(){
        TypedQuery<Vehicle> query = em.createNamedQuery("Vehicle.findAll", Vehicle.class);
        List<Vehicle> allvehicles = query.getResultList();
        return allvehicles;
    }

    public Vehicle updateVehicle(Vehicle vehicle){
        em.merge(vehicle);
        return vehicle;
    }

    public void deleteVehicle(Vehicle v){
        em.remove(v);
    }
}
