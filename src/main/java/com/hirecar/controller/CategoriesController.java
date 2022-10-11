package com.hirecar.controller;

import com.hirecar.model.Categories;
import com.hirecar.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    @Autowired
    CategorieService categorieService;

    @GetMapping("/list")
    public ResponseEntity<?> list(){
        List<Categories> categories = categorieService.list();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> search(Long id) {
        Optional<Categories> categories = categorieService.search(id);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Categories categories) {
        return new ResponseEntity<>(categorieService.insert(categories), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        categorieService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Categories newCategories){
        Categories categories = categorieService.update(id, newCategories);
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
}
