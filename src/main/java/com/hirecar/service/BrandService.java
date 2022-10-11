package com.hirecar.service;

import com.hirecar.model.Brand;
import com.hirecar.repository.BrandRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    @Autowired
    BrandRepository brandRepository;

    public List<Brand> list() {
        List<Brand> brands = brandRepository.findAll();
        return brands;
    }

    public Optional<Brand> search(Long id) {
        Optional<Brand> brand = brandRepository.findById(id);
        return brand;
    }

    public Brand insert(Brand brand) {
        return brandRepository.save(brand);
    }

    public Brand update(Long id, Brand newBrand){
        Brand brand = brandRepository.findById(id).get();
        BeanUtils.copyProperties(newBrand, brand, "id");
        return brandRepository.save(brand);
    }

    public void delete(Long id) {
        brandRepository.deleteById(id);
    }


}
