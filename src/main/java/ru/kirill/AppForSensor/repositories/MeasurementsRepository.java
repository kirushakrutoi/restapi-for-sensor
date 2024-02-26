package ru.kirill.AppForSensor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kirill.AppForSensor.models.Measurements;

@Repository
public interface MeasurementsRepository extends JpaRepository<Measurements, Integer> {

}
