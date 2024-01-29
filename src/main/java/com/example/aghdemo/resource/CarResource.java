package com.example.aghdemo.resource;

import com.example.aghdemo.model.CarByIdResponse;
import com.example.aghdemo.model.CarDto;
import com.example.aghdemo.model.CarRequest;
import com.example.aghdemo.model.CarResponse;
import com.example.aghdemo.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarResource {
    @Autowired
    CarService carService;

    @GetMapping("/test")
    public String test(){return "Hello World!";}

    @GetMapping("/mock")
    public ResponseEntity<CarResponse> getMockCars(){
        return ResponseEntity.ok(new CarResponse(carService.getAllMock()));
    }

    @GetMapping("")
    public ResponseEntity<CarResponse> getCars() {
        return ResponseEntity.ok(new CarResponse(carService.getAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarByIdResponse> getCarById(@PathVariable Long id){
        Optional<CarDto> carDto = carService.getCarById(id);
        /*
        if(carDto.isPresent()){
            return ResponseEntity.ok(new CarByIdResponse(carDto.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
        */

        return carDto.map(dto -> ResponseEntity.ok(new CarByIdResponse(dto)))
                .orElseGet(() -> ResponseEntity.notFound()
                        .build());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> addCar(@RequestBody CarRequest request) {
        long id = carService.addCar(request.getCar());

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id).toUri();

        return ResponseEntity.created(location).build();
    }
}
