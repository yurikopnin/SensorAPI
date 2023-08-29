package ru.kopnin.springcourse.RestAPISensor.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import ru.kopnin.springcourse.RestAPISensor.models.Sensor;

import java.time.LocalDateTime;

public class MeasurementDTO {
    @NotNull
    @Min(-100)
    @Max(100)
    private Double value;
    @NotNull
    private Boolean raining;
    @NotNull
    private SensorDTO sensor;

    public Double getValue() {

        return value;
    }

    public void setValue(Double value) {

        this.value = value;
    }

    public Boolean getRaining() {

        return raining;
    }

    public void setRaining(Boolean raining) {

        this.raining = raining;
    }

    public SensorDTO getSensor() {

        return sensor;
    }

    public void setSensor(SensorDTO sensor) {

        this.sensor = sensor;
    }
}
