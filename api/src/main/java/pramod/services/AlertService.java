package pramod.services;

import org.springframework.stereotype.Component;
import pramod.entity.Alert;
import pramod.entity.VehicleStatistics;

import java.util.List;

@Component
public interface AlertService {

    Alert createAlert(Alert alert);
    //List<Alert> getAllAlerts();
    List<VehicleStatistics> getAllAlerts();
    List<Alert> getAllAlertsByVin(String vin);
    Alert getOneAlert(String alertID);
}
