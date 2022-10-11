package com.hirecar.service;

import com.hirecar.model.Car;
import com.hirecar.model.Rentals;
import com.hirecar.repository.CarRepository;
import com.hirecar.repository.RentalsRepository;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.hirecar.exceptionCustom.ExceptionCustom.verify;


@Service
public class RentalService {

    @Autowired
    private RentalsRepository rentalsRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarService carService;

    public Optional<Rentals> search(Long id) {
        var rental = rentalsRepository.findById(id);
        verify(!rental.isPresent(), HttpStatus.NOT_FOUND, "Tutor não encontrado, informe um id valido!");
        return rental;
    }

    public Rentals toHire(Rentals rental) {
        verify(Objects.isNull(rental), HttpStatus.NOT_FOUND, "Objetivo passado está vazio");
        Car car = carService.search(rental.getCar().getId()).get();
        verify(car.getAvaliable() == false, HttpStatus.BAD_REQUEST, "Carro já alugado");
        BigDecimal total = totalRent(rental);
        rental.setTotal(total);
        rental.setCreated_at(LocalDateTime.now());
        car.setAvaliable(false);
        carRepository.save(car);

        return rentalsRepository.save(rental);
    }

    public BigDecimal totalRent(Rentals rentals) {
        var car = carService.search(rentals.getCar().getId());
        Period period = Period.between(rentals.getStart_date().toLocalDate(), rentals.getEnd_date().toLocalDate());
        var days = BigDecimal.valueOf(period.getDays());
        var daily_rate = car.get().getDaily_rate();
        var total = daily_rate.multiply(days);
        return total;
    }


    public List<Rentals> listRentals() {
        var rentals= rentalsRepository.findAll();
        verify(rentals.isEmpty(), HttpStatus.NOT_FOUND, "Lista de Alugueis está vazia");
        return rentals;
    }

    public Rentals updateEndDate(Long id, LocalDateTime newEndDate) {
        Rentals rental = search(id).get();
        var newTotal = extendRent(rental, newEndDate);
        rental.setEnd_date(newEndDate);
        rental.setTotal(newTotal);
        rental.setUpdate_at(LocalDateTime.now());
        return rentalsRepository.save(rental);
    }

    public BigDecimal extendRent(Rentals rentals, LocalDateTime newEndDate) {
        Period period = Period.between(rentals.getEnd_date().toLocalDate(), newEndDate.toLocalDate());
        var days = BigDecimal.valueOf(period.getDays());
        var daily_rate = rentals.getCar().getDaily_rate();
        var newTotal = daily_rate.multiply(days);
        var totalFinal = rentals.getTotal().add(newTotal);
        return totalFinal;
    }

    public void cancelRent(Long id) {
        Rentals rentals = rentalsRepository.findById(id).get();
        isAllowCancellation(rentals);
        Car car = carService.search(rentals.getCar().getId()).get();
        car.setAvaliable(true);
        rentalsRepository.delete(rentals);

    }

    public void isAllowCancellation(Rentals rentals) {
        verify(rentals.getStart_date().isBefore(LocalDateTime.now()),
                HttpStatus.NOT_ACCEPTABLE, "Não pode ser mais cancelado");
    }

}
