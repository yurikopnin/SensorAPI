package ru.kopnin.springcourse.RestAPISensor.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.kopnin.springcourse.RestAPISensor.DTO.MeasurementDTO;
import ru.kopnin.springcourse.RestAPISensor.models.Measurement;
import ru.kopnin.springcourse.RestAPISensor.service.SensorService;

@Component
public class MeasurementValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Measurement measurement = (Measurement) target;

        if ((measurement.getSensor().getName() == null)) {
            return;
        }
        if (sensorService.findByName(measurement.getSensor().getName()).isEmpty()) {
            errors.rejectValue("sensor", "This name is already used",
                    "Sensor is not add to the system or field name is empty");
        }
    }
}
