package ru.kirill.AppForSensor.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "sensors")
public class Sensor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", unique = true)
    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 30, message = "Non valid name")
    private String name;

    @OneToMany(mappedBy = "sensor")
    private List<Measurements> measurements;

    public Sensor(String name) {
        this.name = name;
    }
}
