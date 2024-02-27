package ru.kirill.AppForSensor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.kirill.AppForSensor.models.Measurements;
import ru.kirill.AppForSensor.repositories.MeasurementsRepository;

import java.util.List;

@Service
public class MeasurementsService {

    private final MeasurementsRepository measurementsRepository;

    @Autowired
    public MeasurementsService(MeasurementsRepository measurementsRepository) {
        this.measurementsRepository = measurementsRepository;
    }

    public void save(Measurements measurements) throws DataAccessException {
        measurementsRepository.save(measurements);
    }

    public List<Measurements> index(){
        return measurementsRepository.findAll();
    }

    public int countRainDays(){
        List<Measurements> measurements = index();

        int count = 0;
        for(Measurements measurement : measurements){
            if(measurement.isRaining()) count++;
        }

        return count;
    }

    public void test(){
        System.out.println("Test");
    }
}
