package pramod.repository;

import pramod.entity.Alert;

import java.util.List;

public interface AlertRepository {
    Alert createAlert(Alert alert);
    List<Alert> getAllAlerts();
    List<Alert> getAlertsByVin(String vin);
    Alert getOneAlert(String alertID);
}
