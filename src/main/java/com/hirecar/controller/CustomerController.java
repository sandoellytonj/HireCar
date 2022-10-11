package com.hirecar.controller;

import com.hirecar.model.Customer;
import com.hirecar.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/list")
    public ResponseEntity<?> list(){
        List<Customer> customers = customerService.list();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> search(Long id) {
        Optional<Customer> customer = customerService.search(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.insert(customer), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        customerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Customer newCustomer){
        Customer customer = customerService.update(id, newCustomer);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
