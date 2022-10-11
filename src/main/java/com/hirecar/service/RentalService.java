package com.hirecar.service;

import com.hirecar.model.Car;
import com.hirecar.model.Rentals;
import com.hirecar.repository.RentalsRepository;
import org.aspectj.bridge.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;


@Service
public class RentalService {

    @Autowired
    private RentalsRepository rentalsRepository;
    
    @Autowired
    private CarService carService;

    public Optional<Rentals> search(Long id) {
        return rentalsRepository.findById(id);
    }

    public Rentals toHire(Rentals rental) {
        Car car = carService.search(rental.getCar().getId()).get();
        BigDecimal daily_rate = car.getDaily_rate();
        BigDecimal days = period(rental.getStart_date(), rental.getEnd_date());
        BigDecimal total = daily_rate.multiply(days);

        rental.setTotal(total);
        rental.setCreated_at(LocalDateTime.now());
        car.setAvaliable(false);

        return rentalsRepository.save(rental);
    }

    public BigDecimal period(LocalDateTime start, LocalDateTime end) {
        Period period = Period.between(start.toLocalDate(), end.toLocalDate());
        return BigDecimal.valueOf(period.getDays());
    }

    public List<Rentals> listRentals() {
        return rentalsRepository.findAll();
    }

    public Rentals updateEndDate(Long id, LocalDateTime newEndDate) {
        Rentals rental = search(id).get();
        BigDecimal days = period(rental.getEnd_date(), newEndDate);
        Car car = carService.search(rental.getCar().getId()).get();
        BigDecimal daily_rate = car.getDaily_rate();
        BigDecimal newTotal = daily_rate.multiply(days);
        newTotal = rental.getTotal().add(newTotal);
        rental.setEnd_date(newEndDate);
        rental.setTotal(newTotal);
        rental.setUpdate_at(LocalDateTime.now());
        return rentalsRepository.save(rental);
    }

    public void cancelRent(Long id) {
        Rentals rentals = rentalsRepository.findById(id).get();
        isAllowCancellation(rentals);
        Car car = carService.search(rentals.getCar().getId()).get();
        car.setAvaliable(true);
        rentalsRepository.delete(rentals);

    }

    public Throwable isAllowCancellation(Rentals rentals) {
        if (rentals.getStart_date().isBefore(LocalDateTime.now())) {
            return new Exception(String.valueOf(Message.FAIL));
        }
        return null;
    }

}
