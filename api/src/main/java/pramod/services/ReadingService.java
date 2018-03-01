package pramod.services;

import org.springframework.stereotype.Component;
import pramod.entity.Reading;

import java.sql.Date;
import java.util.List;

@Component
public interface ReadingService {
    Reading createReading(Reading reading);
    Reading findOneReading(String vin);
    List<Reading> findAllReadings();
    List<Reading> findTopReading();
    List<Reading> findRangeReadings(String vin,String prop,String from,String to);
    Reading updateReading(String readingid, Reading reading);
    void deleteReading(String vinid);
}
