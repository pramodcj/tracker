package pramod.repository;

import pramod.entity.Reading;

import java.util.List;

public interface ReadingRepository {

    Reading createReading(Reading reading);
    Reading findOneReading(String readingID);
    List<Reading> findAllReadings();
    Reading updateReading(Reading reading);
    void deleteReading(Reading reading);
}
