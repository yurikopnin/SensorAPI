package ru.kopnin.springcourse.RestAPISensor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kopnin.springcourse.RestAPISensor.models.Measurement;
import ru.kopnin.springcourse.RestAPISensor.repositories.MeasurementRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasurementService {

    private final SensorService sensorService;
    private final MeasurementRepository measurementRepository;


    @Autowired
    public MeasurementService(SensorService sensorService, MeasurementRepository measurementRepository) {
        this.sensorService = sensorService;
        this.measurementRepository = measurementRepository;
    }

    public List<Measurement> findAll() {

        return measurementRepository.findAll();
    }

    public Integer rainyDaysCount() {
        return measurementRepository.countByRainingTrue();
    }

    @Transactional
    public void addMeasurement(Measurement measurement) {
        enrichmentMeasurement(measurement);
        measurementRepository.save(measurement);
    }

    private void enrichmentMeasurement(Measurement measurement) {
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).get());
        measurement.setMeasurement_date_time(LocalDateTime.now());
    }
}
