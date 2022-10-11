package com.hirecar.service;

import com.hirecar.model.Brand;
import com.hirecar.model.CarImage;
import com.hirecar.repository.CarImageRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarImageService {

    @Autowired
    CarImageRepository carImageRepository;

    public List<CarImage> list() {
        List<CarImage> carImages = carImageRepository.findAll();
        return carImages;
    }

    public Optional<CarImage> search(Long id) {
        Optional<CarImage> carImage = carImageRepository.findById(id);
        return carImage;
    }

    public CarImage insert(CarImage carImage) {
        return carImageRepository.save(carImage);
    }

    public CarImage update(Long id, CarImage newCarImage){
        CarImage carImage = carImageRepository.findById(id).get();
        BeanUtils.copyProperties(newCarImage, carImage, "id");
        return carImageRepository.save(carImage);
    }

    public void delete(Long id) {
        carImageRepository.deleteById(id);
    }


}
