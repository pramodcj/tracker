package pramod.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pramod.entity.Alert;
import pramod.entity.VehicleStatistics;
import pramod.repository.AlertRepository;

import java.util.List;

@Service
public class AlertServiceImpl implements AlertService {

    @Autowired
    AlertRepository alertRepository;

    public Alert createAlert(Alert alert) {
        return alertRepository.createAlert(alert);
    }

    public List<VehicleStatistics> getAllAlerts() {
        List<VehicleStatistics> list = alertRepository.getAllAlerts();
        return list!=null?list:null;
    }

    public List<Alert> getAllAlertsByVin(String vin) {
        List<Alert> list = alertRepository.getAlertsByVin(vin);
        return list!=null?list:null;
    }

    public Alert getOneAlert(String alertID) {
        Alert alert = alertRepository.getOneAlert(alertID);
        if(alert!=null)
            return alert;
        else
            return null;
    }


}
