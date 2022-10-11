package com.hirecar.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private LocalDateTime birth_date;
    private String email;
    @Column(name = "driver_license", unique = true)
    private String driver_license;
    private String adress;
    private String phone_number;
    private LocalDateTime created_at;
    private LocalDateTime update_at;
}
