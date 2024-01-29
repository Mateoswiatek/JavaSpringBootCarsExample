package com.example.aghdemo.repository;

import com.example.aghdemo.repository.encja.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    // nasze nowe zapytania rozne
    public default List<Car> staredCar() {
        return null;
    }

}