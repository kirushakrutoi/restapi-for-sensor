package ru.kirill.AppForSensor.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.kirill.AppForSensor.Exceptions.MeasurementsNotAddException;
import ru.kirill.AppForSensor.models.DTOs.ErrorResp;
import ru.kirill.AppForSensor.models.DTOs.MeasurementsDTO;
import ru.kirill.AppForSensor.models.Measurements;
import ru.kirill.AppForSensor.models.Sensor;
import ru.kirill.AppForSensor.services.MeasurementsService;
import ru.kirill.AppForSensor.services.SensorService;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementsService measurementsService;
    private final SensorService sensorService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementsController(MeasurementsService measurementsService, SensorService sensorService, ModelMapper modelMapper) {
        this.measurementsService = measurementsService;
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/add")
    public HttpEntity<HttpStatus> addMeasurements(@RequestBody @Valid MeasurementsDTO measurementsDTO,
                                                  BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            StringBuilder stringBuilder = new StringBuilder();

            for(FieldError fieldError : bindingResult.getFieldErrors()){
                stringBuilder.append(fieldError.getField())
                        .append(" - ")
                        .append(fieldError.getDefaultMessage())
                        .append("; ");
            }

            throw new MeasurementsNotAddException(stringBuilder.toString());
        }

        try {
            Measurements measurements = convertToMeasurements(measurementsDTO);
            measurementsService.save(measurements);
        } catch (DataAccessException | NullPointerException e){
            throw new MeasurementsNotAddException("Sensor not found");
        }

        return new HttpEntity<>(HttpStatus.OK);
    }

   @GetMapping("/rainyDaysCount")
   public String rainyDaysCount(){
        return String.valueOf(measurementsService.countRainDays());
   }

    @ExceptionHandler
    private HttpEntity<ErrorResp> handleException(MeasurementsNotAddException e){
        ErrorResp response = new ErrorResp(
                e.getMessage()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Measurements convertToMeasurements(MeasurementsDTO measurementsDTO) throws NullPointerException{
        Measurements measurements = modelMapper.map(measurementsDTO, Measurements.class);
        Sensor sensor = sensorService.findByName(measurements.getSensor().getName());
        measurements.setSensor(sensor);
        return measurements;
    }
}
