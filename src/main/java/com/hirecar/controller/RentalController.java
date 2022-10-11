package com.hirecar.controller;

import com.hirecar.dto.persist.RentalPersist;
import com.hirecar.mapper.RentalsMapper;
import com.hirecar.model.Rentals;
import com.hirecar.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rental")
public class RentalController {

    @Autowired
    RentalService rentalService;

    private RentalsMapper rentalsMapper;

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody RentalPersist rentalPersist) {
        Rentals rental = rentalsMapper.MAPPER.persistToEntity(rentalPersist);
        return new ResponseEntity<>(rentalService.toHire(rental), HttpStatus.CREATED);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> search(@PathVariable Long id){
        Optional<Rentals> rental = rentalService.search(id);
        return new ResponseEntity<>(rental, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> list(){
        List<Rentals> rentals = rentalService.listRentals();
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime newDate){
        Rentals rentals = rentalService.updateEndDate(id, newDate);
        return new ResponseEntity<>(rentals, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        rentalService.cancelRent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
