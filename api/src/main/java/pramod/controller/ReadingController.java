package pramod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pramod.entity.Reading;
import pramod.services.ReadingService;

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

    @RequestMapping(method = RequestMethod.GET,value = "/{readingID}")
    public Reading findOneReading(@PathVariable("readingID")String readingID){
        return readingService.findOneReading(readingID);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Reading> findAllReadings(){
        return readingService.findAllReadings();
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
