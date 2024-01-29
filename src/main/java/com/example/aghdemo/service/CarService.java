package com.example.aghdemo.service;

import com.example.aghdemo.model.CarDto;
import com.example.aghdemo.repository.CarRepository;
import com.example.aghdemo.repository.encja.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarService {
    @Autowired
    private final CarRepository carRepository;

    public List<CarDto> getAll(){
        return carRepository.findAll().stream()
                .map(c -> CarDto.builder()
                        .id(c.getId())
                        .carModel(c.getCarModel())
                        .regNumber(c.getRegNumber())
                        .build())
                .toList();
//                .collect(Collectors.toList()); // od SonarLint
    }

    public Optional<CarDto> getCarById(Long id){
        return carRepository.findById(id).map(c -> CarDto.builder()
                .id(c.getId())
                .carModel(c.getCarModel())
                .regNumber(c.getRegNumber())
                .build());
    }

    public long addCar(CarDto car) {
        Car c = carRepository.save(Car.builder()
                .carModel(car.getCarModel())
                .regNumber(car.getRegNumber())
                .build());
        return c.getId();
    }

    public List<CarDto> getAllMock() {
        return Arrays.asList(
                new CarDto(1L, "Nazwa1", "numer1111"),
                new CarDto(2L, "Mercedesik", "7779999"),
                new CarDto(3L, "Citroen C5", "KRK33525"));
    }
}
