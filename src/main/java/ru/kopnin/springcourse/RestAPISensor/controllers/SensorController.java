package ru.kopnin.springcourse.RestAPISensor.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.kopnin.springcourse.RestAPISensor.DTO.SensorDTO;
import ru.kopnin.springcourse.RestAPISensor.models.Sensor;
import ru.kopnin.springcourse.RestAPISensor.service.SensorService;
import ru.kopnin.springcourse.RestAPISensor.util.SensorErrorResponse;
import ru.kopnin.springcourse.RestAPISensor.util.SensorNotCreatedException;
import ru.kopnin.springcourse.RestAPISensor.util.SensorValidator;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;
    private final SensorValidator sensorValidator;

    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorService sensorService, SensorValidator sensorValidator, ModelMapper modelMapper) {
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public List<SensorDTO> getSensors() {

        return sensorService.findAll().stream().map(this::convertToSensorDTO).collect(Collectors.toList());
    }
    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> createSensor(@RequestBody @Valid  SensorDTO sensorDTO, BindingResult bindingResult){
        Sensor sensor = convertToSensor(sensorDTO);
        sensorValidator.validate(sensor, bindingResult);
        if(bindingResult.hasErrors()){
            StringBuilder errMessage = new StringBuilder();
            List<FieldError> errors;
            errors = bindingResult.getFieldErrors();
            for (FieldError error: errors){
                errMessage.append(error.getField()).
                        append("-").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new SensorNotCreatedException(errMessage.toString());
        }
        sensorService.save(sensor);

        return ResponseEntity.ok(HttpStatus.OK);
    }
    private Sensor convertToSensor(SensorDTO sensorDTO){
        Sensor sensor = modelMapper.map(sensorDTO, Sensor.class);
        return sensor;
    }
    private SensorDTO convertToSensorDTO(Sensor sensor){
        SensorDTO sensorDTO = modelMapper.map(sensor, SensorDTO.class);
        return sensorDTO;
    }
    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e) {
        SensorErrorResponse response = new SensorErrorResponse(e.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
