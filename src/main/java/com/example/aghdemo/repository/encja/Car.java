package com.example.aghdemo.repository.encja;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "car_model")
    private String carModel;

    @Column(name = "reg_number")
    private String regNumber;
}
