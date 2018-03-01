package pramod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import pramod.entity.Reading;
import pramod.services.ReadingService;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000","http://mocker.egen.io"},maxAge = 3600)
@RestController
@RequestMapping("/readings")
public class ReadingController {
    @Autowired
    ReadingService readingService;

    @RequestMapping(method = RequestMethod.POST)
    public Reading createReading(@RequestBody Reading reading){
        return readingService.createReading(reading);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Reading> findAllReadings(){
        return readingService.findAllReadings();
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{vin}")
    public Reading findOneReading(@PathVariable("vin")String vin){
        return readingService.findOneReading(vin);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/all_location")
    public List<Reading> findTopReading(){
        return readingService.findTopReading();
    }

    @RequestMapping(method = RequestMethod.GET,value = "/find")
    public List<Reading> findRangeReadings(@RequestParam String vin,
                                          @RequestParam String prop,
                                          @RequestParam String from,
                                          @RequestParam String to){
        return readingService.findRangeReadings(vin,prop,from,to);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "/{id}")
    public Reading updateReading(@RequestBody String readingID, Reading reading){
        return readingService.updateReading(readingID,reading);
    }

    @RequestMapping(method = RequestMethod.DELETE,value = "/{id}")
    public void deleteReading(@PathVariable("id")String readingID){
        readingService.deleteReading(readingID);
    }
}
