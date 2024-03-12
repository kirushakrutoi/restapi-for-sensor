package ru.kirill.AppForSensor.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kirill.AppForSensor.Exceptions.SensorNotCreatedException;
import ru.kirill.AppForSensor.models.DTOs.ErrorResp;
import ru.kirill.AppForSensor.models.DTOs.SensorDTO;
import ru.kirill.AppForSensor.models.Sensor;
import ru.kirill.AppForSensor.services.SensorService;

import java.sql.SQLException;
import java.util.Arrays;

@RestController
@RequestMapping("/sensors")
public class RegistrationController {

    private final ModelMapper modelMapper;
    private final SensorService sensorService;

    @Autowired
    public RegistrationController(ModelMapper modelMapper, SensorService sensorService) {
        this.modelMapper = modelMapper;
        this.sensorService = sensorService;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO,
                                                    BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            throw new SensorNotCreatedException("Sensor name not valid");
        }

        Sensor sensor = convertToSensor(sensorDTO);

        try {
            sensorService.save(sensor);
        } catch (DataAccessException e){
            throw new SensorNotCreatedException("Sensor is exist");
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResp> handleException(SensorNotCreatedException e){
        ErrorResp response = new ErrorResp(
                e.getMessage()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private SensorDTO convertToSensorDTO(Sensor sensor){
        return modelMapper.map(sensor, SensorDTO.class);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }
}
