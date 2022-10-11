package com.hirecar.service;

import com.hirecar.model.Customer;
import com.hirecar.repository.CustomersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomersRepository customersRepository;

    public List<Customer> list() {
        List<Customer> customers = customersRepository.findAll();
        return customers;
    }

    public Optional<Customer> search(Long id) {
        Optional<Customer> customer = customersRepository.findById(id);
        return customer;
    }

    public Customer insert(Customer customer) {
        return customersRepository.save(customer);
    }

    public Customer update(Long id, Customer newCustomer){
        Customer customer = customersRepository.findById(id).get();
        BeanUtils.copyProperties(newCustomer, customer, "id");
        customer.setUpdate_at(LocalDateTime.now());
        return customersRepository.save(customer);
    }

    public void delete(Long id) {
        customersRepository.deleteById(id);
    }


}
