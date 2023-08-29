package ru.kopnin.springcourse.RestAPISensor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kopnin.springcourse.RestAPISensor.models.Sensor;
import ru.kopnin.springcourse.RestAPISensor.repositories.SensorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }
    public List<Sensor> findAll() {

        return sensorRepository.findAll();
    }
    public Optional<Sensor> findByName(String name) {
        return Optional.ofNullable(sensorRepository.findByName(name));
    }
    @Transactional
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }
}
