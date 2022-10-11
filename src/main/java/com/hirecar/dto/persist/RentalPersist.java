package com.hirecar.dto.persist;

import com.hirecar.model.Car;
import com.hirecar.model.Customer;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RentalPersist {

    Long id;
    private Car car;
    private Customer customer;
    private LocalDateTime start_date;
    private LocalDateTime end_date;
}
