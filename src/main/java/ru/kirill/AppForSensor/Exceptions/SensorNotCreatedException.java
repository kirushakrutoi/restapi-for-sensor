package ru.kirill.AppForSensor.Exceptions;

public class SensorNotCreatedException extends RuntimeException{
    public SensorNotCreatedException(String msg){
        super(msg);
    }
}
