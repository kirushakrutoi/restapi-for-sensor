package ru.kirill.AppForSensor.models.DTOs;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SensorDTO {

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Non valid name")
    private String name;
}
