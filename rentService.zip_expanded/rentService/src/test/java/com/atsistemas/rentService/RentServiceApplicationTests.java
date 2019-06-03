package com.atsistemas.rentService;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.atsistemas.rentService.dao.CarRepository;
import com.atsistemas.rentService.model.Car;
import com.atsistemas.rentService.model.Rate;
import com.atsistemas.rentService.model.Rental;
import com.atsistemas.rentService.service.CarServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RentServiceApplicationTests {
	@Mock
	private CarRepository carRepository;
	@Mock
	private Car mockCar;
	@InjectMocks
	private CarServiceImpl carService;

	@Test
	public void RentalServiceFindByIdTest() {
		// Given
		Integer id = 1;
		Car car = new Car();
		car.setCarPlate("1234ABC");
		car.setId(id);
		car.setRegistrationYear("2000");
		Rate rate = new Rate();
		rate.setId(2);
		Set<Rate> rates = new HashSet<Rate>();
		rates.add(rate);
		Rental rental = new Rental();
		rental.setId(3);
		Set<Rental> rentals = new HashSet<Rental>();
		rentals.add(rental);
		car.setRates(rates);
		car.setRentals(rentals);

		// When
		Mockito.when(carRepository.findById(id)).thenReturn(Optional.ofNullable(car));

		// Then
		Optional<Car> answer = carService.findById(id);

		assertEquals(answer.get().getId(), car.getId());
		assertEquals(answer.get().getRates(), car.getRates());
		assertEquals(answer.get().getRentals(), car.getRentals());
		assertEquals(answer.get().getRegistrationYear(), car.getRegistrationYear());
		assertEquals(answer.get().getCarPlate(), car.getCarPlate());
	}

	@Test
	public void CarServiceGetAllRental() {
		// Given
		Integer id = 1;
		Car car = new Car();
		car.setId(id);
		Rental rental = new Rental();
		rental.setId(3);
		Set<Rental> rentals = new HashSet<Rental>();
		rentals.add(rental);
		car.setRentals(rentals);

		// When
		Mockito.when(carRepository.findById(id)).thenReturn(Optional.ofNullable(car));
		Mockito.when(mockCar.getRentals()).thenReturn(rentals);

		// Then
		Set<Rental> answer = carService.getAllRental(id);

		assertEquals(answer, rentals);
	}

	@Test
	public void CarServiceGetAllRate() {
		// Given
		Integer id = 1;
		Car car = new Car();
		car.setId(id);
		Rate rate = new Rate();
		rate.setId(2);
		Set<Rate> rates = new HashSet<Rate>();
		rates.add(rate);
		car.setRates(rates);

		// When
		Mockito.when(carRepository.findById(id)).thenReturn(Optional.ofNullable(car));
		Mockito.when(mockCar.getRates()).thenReturn(rates);

		// Then
		Set<Rate> answer = carService.getAllRate(id);

		assertEquals(answer, rates);
	}

	@Test
	public void CarServiceCreate() {
		Integer id = 1;
		Car car = new Car();
		car.setCarPlate("1234ABC");
		car.setId(id);
		car.setRegistrationYear("2000");
		Rate rate = new Rate();
		rate.setId(2);
		Set<Rate> rates = new HashSet<Rate>();
		rates.add(rate);
		Rental rental = new Rental();
		rental.setId(3);
		Set<Rental> rentals = new HashSet<Rental>();
		rentals.add(rental);
		car.setRates(rates);
		car.setRentals(rentals);

		// When
		Mockito.when(carRepository.save(car)).thenReturn(car);

		// Then
		Car answer = carService.create(car);

		assertEquals(answer.getId(), car.getId());
		assertEquals(answer.getRates(), car.getRates());
		assertEquals(answer.getRentals(), car.getRentals());
		assertEquals(answer.getRegistrationYear(), car.getRegistrationYear());
		assertEquals(answer.getCarPlate(), car.getCarPlate());	
	}
}