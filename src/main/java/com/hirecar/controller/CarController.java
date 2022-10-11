package com.hirecar.controller;

import com.hirecar.model.Car;
import com.hirecar.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    CarService carService;

    @GetMapping("/list")
    public ResponseEntity<?> list(){
        List<Car> cars = carService.list();
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/avaliableList")
    public ResponseEntity<?> avaliableList(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date){
        List<Car> cars = carService.avaliableIsTrue(date);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> search(Long id) {
        Optional<Car> car = carService.search(id);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Car car) {
        return new ResponseEntity<>(carService.insert(car), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        carService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Car newCar){
        Car car = carService.update(id, newCar);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }
}
