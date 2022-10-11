package com.hirecar.service;

import com.hirecar.model.Car;
import com.hirecar.repository.CarRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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
        return car;
    }

    public Car insert(Car car) {
        return carRepository.save(car);
    }

    public Car update(Long id, Car newCar){
        Car car = carRepository.findById(id).get();
        BeanUtils.copyProperties(newCar, car, "id");
        return carRepository.save(car);
    }

    public void delete(Long id) {
        carRepository.deleteById(id);
    }


    public List<Car> avaliableIsTrue(LocalDateTime date) {
        return carRepository.findCarsByAvaliableIsTrue(date);
    }
}
