package ru.kirill.AppForSensor.models.DTOs;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.kirill.AppForSensor.models.Sensor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementsDTO {
    @Max(value = 100, message = "The temperature should be less than 100 degrees")
    @Min(value = -100, message = "The temperature should be more than -100 degrees")
    @NotNull(message = "Name shouldn't be empty")
    private BigDecimal value;

    private boolean raining;

    private Sensor sensor;

}
