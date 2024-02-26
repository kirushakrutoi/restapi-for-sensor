package ru.kirill.AppForSensor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kirill.AppForSensor.models.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {

}
