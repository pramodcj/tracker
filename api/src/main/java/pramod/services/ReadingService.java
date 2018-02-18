package pramod.services;

import org.springframework.stereotype.Component;
import pramod.entity.Reading;

import java.util.List;

@Component
public interface ReadingService {
    Reading createReading(Reading reading);
    Reading findOneReading(String vinid);
    List<Reading> findAllReadings();
    Reading updateReading(String readingid, Reading reading);
    void deleteReading(String vinid);
}
