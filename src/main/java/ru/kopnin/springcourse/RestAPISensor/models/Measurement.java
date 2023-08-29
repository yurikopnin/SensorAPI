package ru.kopnin.springcourse.RestAPISensor.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "measurements")
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "value")
    @NotNull
    @Min(-100)
    @Max(100)
    private Double value;
    @Column(name = "raining")
    @NotNull
    private Boolean raining;
    @Column(name = "measurement_date_time")
    @NotNull
    private LocalDateTime measurement_date_time;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name")
    private Sensor sensor;

    public Measurement() {
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {

        this.id = id;
    }

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

    public LocalDateTime getMeasurement_date_time() {

        return measurement_date_time;
    }

    public void setMeasurement_date_time(LocalDateTime measurement_date_time) {
        this.measurement_date_time = measurement_date_time;
    }

    public Sensor getSensor() {

        return sensor;
    }

    public void setSensor(Sensor sensor) {

        this.sensor = sensor;
    }
}
