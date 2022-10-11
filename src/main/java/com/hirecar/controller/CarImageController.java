package com.hirecar.controller;

import com.hirecar.model.CarImage;
import com.hirecar.service.CarImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carImage")
public class CarImageController {

    @Autowired
    CarImageService carImageService;

    @GetMapping("/list")
    public ResponseEntity<?> list(){
        List<CarImage> carImages = carImageService.list();
        return new ResponseEntity<>(carImages, HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> search(Long id) {
        Optional<CarImage> carImage = carImageService.search(id);
        return new ResponseEntity<>(carImage, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody CarImage carImage) {
        return new ResponseEntity<>(carImageService.insert(carImage), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        carImageService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody CarImage newCarImage){
        CarImage carImage = carImageService.update(id, newCarImage);
        return new ResponseEntity<>(carImage, HttpStatus.OK);
    }
}
