package pramod.repository;

import pramod.entity.Reading;

import java.sql.Date;
import java.util.List;

public interface ReadingRepository {

    Reading createReading(Reading reading);
    Reading findOneReading(String vin);
    List<Reading> findAllReadings();
    List<Reading> findTopReading();
    List<Reading> findRangeReadings(String vin, String prop, String from, String to);
    Reading updateReading(Reading reading);
    void deleteReading(Reading reading);
}
