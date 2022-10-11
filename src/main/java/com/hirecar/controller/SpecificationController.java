package com.hirecar.controller;

import com.hirecar.model.Specification;
import com.hirecar.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/specification")
public class SpecificationController {

    @Autowired
    SpecificationService specService;

    @GetMapping("/list")
    public ResponseEntity<?> list(){
        List<Specification> specs = specService.list();
        return new ResponseEntity<>(specs, HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> search(Long id) {
        Optional<Specification> spec = specService.search(id);
        return new ResponseEntity<>(spec, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Specification specification) {
        return new ResponseEntity<>(specService.insert(specification), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        specService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Specification newSpecification){
        Specification specification = specService.update(id, newSpecification);
        return new ResponseEntity<>(specification, HttpStatus.OK);
    }
}
