package pramod.repository;

import org.springframework.stereotype.Repository;
import pramod.entity.Reading;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ReadingRepositoryImpl implements ReadingRepository {

    @PersistenceContext
    private EntityManager em;

    public Reading createReading(Reading reading) {
        em.persist(reading);
        return reading;
    }

    public Reading findOneReading(String readingID) {
        TypedQuery<Reading> query = em.createNamedQuery("Reading.findOneById",Reading.class);
        query.setParameter("paramReadingID", readingID);

        List<Reading> listreadings = query.getResultList();

        if(listreadings.size()!=0 && listreadings.size()==1)
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

    public Reading updateReading(Reading reading){
        return em.merge(reading);
    }

    public void deleteReading(Reading reading){
        em.remove(reading);
    }
}
