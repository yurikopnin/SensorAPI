package ru.kopnin.springcourse.RestAPISensor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kopnin.springcourse.RestAPISensor.models.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    public Sensor findByName(String name);
}
