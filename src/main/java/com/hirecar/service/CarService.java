package com.hirecar.service;

import com.hirecar.model.Car;
import com.hirecar.repository.CarRepository;
import lombok.var;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.hirecar.exceptionCustom.ExceptionCustom.verify;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<Car> list() {
        List<Car> cars = carRepository.findAll();
        return cars;
    }

    public Optional<Car> search(Long id) {
        Optional<Car> car = carRepository.findById(id);
        verify(Objects.isNull(car), HttpStatus.NOT_FOUND, "Objetivo passado está vazio");
        return car;
    }

    public Car insert(Car car) {
        verify(Objects.isNull(car), HttpStatus.NOT_FOUND, "Objetivo passado está vazio");
        return carRepository.save(car);
    }

    public Car update(Long id, Car newCar){
        Car car = search(id).get();
        BeanUtils.copyProperties(newCar, car, "id");
        return carRepository.save(car);
    }

    public void delete(Long id) {
        var car = search(id);
        verify(Objects.isNull(car), HttpStatus.NOT_FOUND, "Objetivo passado não foi encontrado");
        carRepository.deleteById(id);
    }


    public List<Car> avaliableIsTrue(LocalDateTime date) {
        return carRepository.findCarsByAvaliableIsTrue(date);
    }
}
