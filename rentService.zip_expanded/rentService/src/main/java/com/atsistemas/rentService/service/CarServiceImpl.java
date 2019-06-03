package com.atsistemas.rentService.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.atsistemas.rentService.dao.CarRepository;
import com.atsistemas.rentService.model.Car;
import com.atsistemas.rentService.model.Rate;
import com.atsistemas.rentService.model.Rental;

@Service
public class CarServiceImpl implements CarService {
	@Autowired
	CarRepository carRepository;
	@Autowired
	RateService rateService;
	@Autowired
	RentalService rentalService;
	@Autowired
	CarRateVerification carRateVerification;

	@Override
	public Optional<Car> findById(Integer id) {
		return carRepository.findById(id);
	}

	public List<Car> findAll(Pageable page) {
		return carRepository.findAll(page).getContent();
	}

	public Set<Rental> getAllRental(Integer idCar) {
		return carRepository.findById(idCar).get().getRentals();
	}

	public Set<Rate> getAllRate(Integer idCar) {
		return carRepository.findById(idCar).get().getRates();
	}

	@Override
	public Car create(Car car) {
		return carRepository.save(car);
	}

	@Override
	public void update(Car car) {
		Car dbCar = carRepository.findById(car.getId()).get();
		dbCar.setCarPlate(car.getCarPlate());
		dbCar.setRegistrationYear(car.getRegistrationYear());
		carRepository.save(dbCar);
	}

	@Override
	public ResponseEntity<?> addRate(Integer idCar, Integer idRate) {
		Car car = carRepository.findById(idCar).get();
		Rate newRate = rateService.findById(idRate).get();
		if (!carRateVerification.verifyCarRate(car, newRate)) {
			return ResponseEntity.badRequest().build();
		}
		System.out.println("añadiendo a coche " + idCar + " el rate " + idRate);
		newRate.getCars().add(car);
		carRepository.save(car);
		return ResponseEntity.ok().build();
	}

	@Override
	public void delete(Integer idCar) {
		carRepository.deleteById(idCar);
	}

	@Override
	public Optional<Car> getBetterCar(Date startDate, Date endDate) {
		List<Rental> rentalsGrouped = rentalService.getGroupedRentals(startDate, endDate);
		HashMap<Car, Double> cars = new HashMap<Car, Double>();
		for (Rental rental : rentalsGrouped) {
			if (cars.containsKey(rental.getCar())) {
				cars.put(rental.getCar(), cars.get(rental.getCar()) + rental.getPrice());
			} else {
				cars.put(rental.getCar(), rental.getPrice());
			}
		}

		Car bestCar = new Car();
		Double highestValue = 0.0;
		for (Car entry : cars.keySet()) {
			if (cars.get(entry) > highestValue) {
				highestValue = cars.get(entry);
				bestCar = entry;
			}
		}
		return Optional.ofNullable(bestCar);
	}
}
