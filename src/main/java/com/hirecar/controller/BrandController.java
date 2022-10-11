package com.hirecar.controller;

import com.hirecar.model.Brand;
import com.hirecar.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping("/list")
    public ResponseEntity<?> list(){
        List<Brand> brands = brandService.list();
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> search(Long id) {
        Optional<Brand> brand = brandService.search(id);
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Brand brand) {
        return new ResponseEntity<>(brandService.insert(brand), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        brandService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Brand newBrand){
        Brand brand = brandService.update(id, newBrand);
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }
}
