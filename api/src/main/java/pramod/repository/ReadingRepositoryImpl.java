package pramod.repository;

import org.springframework.stereotype.Repository;
import pramod.entity.Reading;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Console;
import java.sql.Date;
import java.util.List;

@Repository
public class ReadingRepositoryImpl implements ReadingRepository {

    @PersistenceContext
    private EntityManager em;

    public Reading createReading(Reading reading) {
        System.out.print(reading.toString());
        em.persist(reading);
        return reading;
    }

    public Reading findOneReading(String vin) {
        TypedQuery<Reading> query = em.createNamedQuery("Reading.findOneByVin",Reading.class);
        query.setParameter("paramVin", vin);

        List<Reading> listreadings = query.getResultList();

        if(listreadings.size()!=0)
            return listreadings.get(0);
        else
            return null;
    }

    public List<Reading> findAllReadings(){
        TypedQuery<Reading> query = em.createNamedQuery("Reading.findAll",Reading.class);
        List<Reading> listreadings = query.getResultList();

        if(listreadings.size()!=0)
            return listreadings;
        else
            return null;
    }

    public List<Reading> findTopReading(){
        TypedQuery<Reading> query = em.createNamedQuery("Reading.trackAll",Reading.class);
        List<Reading> listreadings = query.getResultList();

        if(listreadings.size()!=0)
            return listreadings;
        else
            return null;
    }

    public List<Reading> findRangeReadings(String vin, String prop, String from, String to){
        //String readingRange = "select r."+prop+", r.timestamp from Reading r where r.vin='"+vin+"' and (r.timestamp >= STR_TO_DATE('"+from+"', '%Y-%m-%dT%H:%i')) and (r.timestamp <= STR_TO_DATE('"+to+"', '%Y-%m-%dT%H:%i')) order by r.timestamp";

        TypedQuery<Reading> query = em.createNamedQuery("Reading.range",Reading.class);
        query.setParameter("paramVin", vin)
                .setParameter("paramFrom",from)
                .setParameter("paramTo",to);

        List<Reading> listreadings = query.getResultList();

        if(listreadings.size()!=0)
            return listreadings;
        else
            return null;
    }

    public Reading updateReading(Reading reading){
        return em.merge(reading);
    }

    public void deleteReading(Reading reading){
        em.remove(reading);
    }
}


        /*else if(prop.equals("speed"))
        {
        System.out.println(prop);
        query = em.createNamedQuery("Reading.speedRange",Reading.class);
        }
        else if(prop.equals("engineHp"))
        {
        System.out.println(prop);
        query = em.createNamedQuery("Reading.hpRange",Reading.class);
        }
        else if(prop.equals("engineRpm"))
        {
        System.out.println(prop);
        query = em.createNamedQuery("Reading.rpmRrange",Reading.class);
        }*/