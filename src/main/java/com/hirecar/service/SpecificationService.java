package com.hirecar.service;

import com.hirecar.model.Specification;
import com.hirecar.repository.SpecificationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecificationService {

    @Autowired
    SpecificationRepository specificationRepository;

    public List<Specification> list() {
        List<Specification> spec = specificationRepository.findAll();
        return spec;
    }

    public Optional<Specification> search(Long id) {
        Optional<Specification> spec = specificationRepository.findById(id);
        return spec;
    }

    public Specification insert(Specification specification) {
        return specificationRepository.save(specification);
    }

    public Specification update(Long id, Specification newSpec){
        Specification spec = specificationRepository.findById(id).get();
        BeanUtils.copyProperties(newSpec, spec, "id");
        return specificationRepository.save(spec);
    }

    public void delete(Long id) {
        specificationRepository.deleteById(id);
    }


}
