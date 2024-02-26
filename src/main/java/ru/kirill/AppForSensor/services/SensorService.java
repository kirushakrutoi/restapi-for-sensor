package ru.kirill.AppForSensor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kirill.AppForSensor.models.Sensor;
import ru.kirill.AppForSensor.repositories.SensorRepository;

import java.sql.SQLException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Sensor sensor) throws DataAccessException{
        sensorRepository.save(sensor);
    }

    public Sensor findByName(String name){
        return sensorRepository.findByName(name);
    }
}
