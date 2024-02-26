package ru.kirill.AppForSensor.Exceptions;

public class MeasurementsNotAddException extends RuntimeException{
    public MeasurementsNotAddException(String msg) {
        super(msg);
    }
}
