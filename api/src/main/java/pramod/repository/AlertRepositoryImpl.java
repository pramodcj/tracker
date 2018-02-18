package pramod.repository;

import org.springframework.stereotype.Repository;
import pramod.entity.Alert;
import pramod.exception.ResourceNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class AlertRepositoryImpl implements AlertRepository{

    @PersistenceContext
    EntityManager em;

    public Alert createAlert(Alert alert) {
        em.persist(alert);
        return alert;
    }

    public Alert getOneAlert(String alertID) {
        TypedQuery<Alert> query = em.createNamedQuery("Alert.findOne",Alert.class);
        query.setParameter("paramalertID",alertID);
        List<Alert> list = query.getResultList();

        if(list.size()==1 && list!=null)
        {
            return list.get(0);
        }
        else
        {
            return null;
        }
    }


    public List<Alert> getAllAlerts() {
        Timestamp ts = new Timestamp(System.currentTimeMillis()-7200000);
        TypedQuery<Alert> query = em.createNamedQuery("Alert.findAllHighAlerts",Alert.class);
        query.setParameter("paramalertTimeStamp",ts);
        List<Alert> list = query.getResultList();
        if(list!=null)
        {
            return list;
        }
        else
        {
            throw new ResourceNotFoundException("No Alerts At this Moment");
        }
    }

    public List<Alert> getAlertsByVin(String vin) {
        TypedQuery<Alert> query = em.createNamedQuery("Alert.findAllByVin",Alert.class);
        query.setParameter("alertparamVin",vin);

        List<Alert> list = query.getResultList();

        return list!=null?list:null;
    }
}
