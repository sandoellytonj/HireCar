package com.hirecar.serviceTest;

import com.hirecar.model.*;
import com.hirecar.repository.CarRepository;
import com.hirecar.repository.RentalsRepository;
import com.hirecar.service.RentalService;
import junit.framework.TestCase;
import lombok.var;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class RentalsTest {

    @InjectMocks
    RentalService rentalService;

    @Mock
    RentalsRepository rentalsRepository;

    Rentals rental;

    Car car;

    private Object Exception;

    @Before
    public void before() {
        MockitoAnnotations.openMocks(this);

        Categories categories = Categories.builder()
                .id(1L)
                .name("turbo")
                .created_at(LocalDateTime.now())
                .build();

        Brand brand = Brand.builder()
                .id(1L)
                .name("Audi")
                .created_at(LocalDateTime.now())
                .build();

        Specification specification = Specification.builder()
                .id(1L)
                .name("4 Portas")
                .created_at(LocalDateTime.now())
                .build();

        Customer customer = Customer.builder()
                .id(1L)
                .name("Sandoellyton")
                .birth_date(LocalDateTime.of(1996, 10, 18, 8, 0))
                .email("sandoellyton@gmail.com")
                .driver_license("XXXX")
                .adress("Rua teste")
                .phone_number("889999888777")
                .created_at(LocalDateTime.now())
                .update_at(null)
                .build();



        car = Car.builder()
                .id(1L)
                .name("A3")
                .description("2.0 turbo")
                .daily_rate(BigDecimal.valueOf(200))
                .avaliable(true)
                .license_plate("XXXX")
                .brand(brand)
                .categories(categories)
                .specificationSet(Collections.singleton(specification))
                .color("Preto")
                .created_at(LocalDateTime.now())
                .build();

        rental = Rentals.builder()
                .id(1L)
                .car(car)
                .customer(customer)
                .start_date(LocalDateTime.now())
                .end_date(LocalDateTime.now().plusDays(15))
                .created_at(LocalDateTime.now())
                .update_at(null)
                .build();
    }

    @Test
    public void TesteConsultarRental() {

        Mockito.when(rentalsRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(rental));

        Optional<Rentals> response = rentalService.search(rental.getId());


        TestCase.assertEquals(response.get().getId(), rental.getId());
        TestCase.assertEquals(response.get().getCustomer(), rental.getCustomer());
        TestCase.assertEquals(response.get().getStart_date(), rental.getStart_date());
        TestCase.assertEquals(response.get().getEnd_date(), rental.getEnd_date());
        TestCase.assertEquals(response.get().getTotal(), rental.getTotal());
        TestCase.assertEquals(response.get().getCreated_at(), rental.getCreated_at());
        TestCase.assertEquals(response.get().getUpdate_at(), rental.getUpdate_at());
        TestCase.assertEquals(response.get().getCar(), rental.getCar());
    }

    @Test
    public void TestLisRental() {

        List<Rentals> lista = Lists.newArrayList(rental);

        Mockito.when(rentalsRepository.findAll()).thenReturn(lista);

        List<Rentals> rentals = rentalService.listRentals();

        TestCase.assertEquals(rentals.size(), lista.size());
    }


    @Test(expected = Exception.class)
    public void TestCanceledRent() {
        Mockito.when(rentalsRepository.findById(Mockito.anyLong())).thenReturn(java.util.Optional.ofNullable(rental));
        Mockito.when(rentalService.isAllowCancellation(Mockito.any(Rentals.class))).thenReturn((Throwable) Exception);

        rentalService.cancelRent(rental.getId());
    }
}
