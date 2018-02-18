package pramod.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pramod.entity.Alert;
import pramod.entity.Reading;
import pramod.entity.Vehicle;
import pramod.exception.BadRequestException;
import pramod.exception.ResourceNotFoundException;
import pramod.repository.AlertRepository;
import pramod.repository.ReadingRepository;
import pramod.repository.VehicleRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReadingServiceImpl implements ReadingService {

    @Autowired
    ReadingRepository readingRepository;


    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    AlertRepository alertsRepository;

    @Transactional
    public Reading createReading(Reading reading) {
        Reading exist = readingRepository.findOneReading(reading.getReadingId());
        if(exist!= null)
        {
            throw new BadRequestException("Reading already exists with same parameters");
        }

        ArrayList<Alert> allalerts = giveAllAlerts(reading);
        if(allalerts.size()!=0) {
            for (Alert alert : allalerts) {
                alertsRepository.createAlert(alert);
            }
            reading.setAlerts(allalerts);
        }
        reading.setAlerts(allalerts);
        readingRepository.createReading(reading);
        return reading;
    }

    public Reading findOneReading(String readingID) {
        Reading exist = readingRepository.findOneReading(readingID);
        if(exist!=null)
        {
            return exist;
        }
        else
            throw new ResourceNotFoundException("No Available Readings");
    }


    public List<Reading> findAllReadings() {
        List<Reading> allreadings  = readingRepository.findAllReadings();
        if(allreadings.size()!=0)
        {
            return allreadings;
        }
        else
            throw new ResourceNotFoundException("No Readings are Available");
    }

    @Transactional
    public Reading updateReading(String readingid,Reading reading) {
        Reading readingexist = readingRepository.findOneReading(readingid);


        if(readingexist!=null)
        {
            return  readingRepository.updateReading(reading);
        }
        else
        {
            throw new BadRequestException("Requested resource is not available to update");
        }
    }

    @Transactional
    public void deleteReading(String readingID) {
        Reading reading = readingRepository.findOneReading(readingID);
        if(reading!=null) {
            readingRepository.deleteReading(reading);

        }else
            throw new BadRequestException("Requested resource is not available to delete");
    }





    private ArrayList<Alert> giveAllAlerts(Reading reading)
    {
        ArrayList<Alert> alertlist = new ArrayList<Alert>();
        int errorboolean = 0; //can be 0 or 1; 0 - false, 1- true. initially it would be false;
        //ArrayList<String> alertlist = new ArrayList<String>();//list to store the errors

        Vehicle vehicle = vehicleRepository.findOneVehicle(reading.getVin());
        Timestamp  ts;

        Alert x;
        if(vehicle!=null)
        {
            if(reading.getEngineRpm()>vehicle.getRedlineRpm())
            {
                x = new Alert();
                x.setAlertLevel("HIGH");
                ts= new Timestamp(System.currentTimeMillis());
                x.setAlertTime(ts);
                x.setReason("EngineRPM > RedLineRPM");
                x.setUseremail("N/A"); //entering N/A as we dont have the user information right now
                x.setVin(vehicle.getVin());

                alertlist.add(x);
            }
            double calcvalue = (0.1)*(vehicle.getMaxFuelVolume());
            if(reading.getFuelVolume()< calcvalue)
            {
                x = new Alert();
                x.setAlertLevel("MEDIUM");
                ts= new Timestamp(System.currentTimeMillis());
                x.setAlertTime(ts);
                x.setReason("fuelVolume < 10% of maxFuelVolume");
                x.setUseremail("N/A"); //entering N/A as we dont have the user information right now
                x.setVin(vehicle.getVin());

                alertlist.add(x);
            }
            if(reading.getTires().getFrontLeft()<32 ||reading.getTires().getFrontLeft()>36
                    ||reading.getTires().getFrontRight()<32 ||reading.getTires().getFrontRight()>36
                    ||reading.getTires().getRearLeft()<32 ||reading.getTires().getRearLeft()>36
                    ||reading.getTires().getRearRight()<32 ||reading.getTires().getRearRight()>36)
            {
                x = new Alert();
                x.setAlertLevel("LOW");
                ts= new Timestamp(System.currentTimeMillis());
                x.setAlertTime(ts);
                x.setReason("tire pressure of one or more tires is < 32psi or less than 36psi ");
                x.setUseremail("N/A"); //entering N/A as we dont have the user information right now
                x.setVin(vehicle.getVin());

                alertlist.add(x);
            }
            if(reading.isEngineCoolantLow() || reading.isCheckEngineLightOn())
            {
                x = new Alert();
                x.setAlertLevel("LOW");
                ts= new Timestamp(System.currentTimeMillis());
                x.setAlertTime(ts);
                x.setReason("Either engine coolant is low or check engine is on");
                x.setUseremail("N/A"); //entering N/A as we dont have the user information right now
                x.setVin(vehicle.getVin());

                alertlist.add(x);
            }
        }
        return alertlist;
    }




}
