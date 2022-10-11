package com.hirecar.service;

import com.hirecar.model.Categories;
import com.hirecar.repository.CategoriesRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorieService {

    @Autowired
    CategoriesRepository categoriesRepository;

    public List<Categories> list() {
        List<Categories> categories = categoriesRepository.findAll();
        return categories;
    }

    public Optional<Categories> search(Long id) {
        Optional<Categories> categories = categoriesRepository.findById(id);
        return categories;
    }

    public Categories insert(Categories categories) {
        return categoriesRepository.save(categories);
    }

    public Categories update(Long id, Categories newCategories){
        Categories categories = categoriesRepository.findById(id).get();
        BeanUtils.copyProperties(newCategories, categories, "id");
        return categoriesRepository.save(categories);
    }

    public void delete(Long id) {
        categoriesRepository.deleteById(id);
    }


}
