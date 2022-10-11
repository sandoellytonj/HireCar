package com.hirecar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private String description;
    private BigDecimal daily_rate;
    private Boolean avaliable;
    private String license_plate;

    @ManyToOne
    private Brand brand;

    @ManyToOne
    private Categories categories;

    @ManyToMany
    private Set<Specification> specificationSet;

    private String color;
    private LocalDateTime created_at;

}
