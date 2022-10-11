package com.hirecar.repository;

import com.hirecar.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "SELECT C.* FROM car C JOIN rentals r on C.id = r.car_id WHERE C.avaliable IS TRUE AND r.end_date < :data", nativeQuery = true)
    List<Car> findCarsByAvaliableIsTrue(@Param("data") LocalDateTime data);
}
