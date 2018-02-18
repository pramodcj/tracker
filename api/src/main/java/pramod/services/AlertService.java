package pramod.services;

import org.springframework.stereotype.Component;
import pramod.entity.Alert;

import java.util.List;

@Component
public interface AlertService {

    Alert createAlert(Alert alert);
    List<Alert> getAllAlerts();
    List<Alert> getAllAlertsByVin(String vin);
    Alert getOneAlert(String alertID);
}
