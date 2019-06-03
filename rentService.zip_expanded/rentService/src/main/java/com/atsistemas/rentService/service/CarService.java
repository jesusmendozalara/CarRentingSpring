package com.atsistemas.rentService.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.atsistemas.rentService.model.Car;
import com.atsistemas.rentService.model.Rate;
import com.atsistemas.rentService.model.Rental;

public interface CarService {
	/**
	 * Returns the car if found.
	 * 
	 * @param id
	 * @return
	 */
	Optional<Car> findById(Integer id);

	/**
	 * Returns a list with all cars.
	 * 
	 * @return
	 */
	List<Car> findAll(Pageable page);

	/**
	 * Returns the most rented car between the given dates.
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	Optional<Car> getBetterCar(Date startDate, Date endDate);

	/**
	 * Returns a set with all rentals from a car.
	 * 
	 * @return
	 */
	Set<Rental> getAllRental(Integer idCar);

	/**
	 * Returns a set with all rates from a car.
	 * 
	 * @return
	 */
	Set<Rate> getAllRate(Integer idCar);

	/**
	 * Creates a car.
	 * 
	 * @param car
	 * @return
	 */
	Car create(Car car);

	/**
	 * Updates a car.
	 * 
	 * @param car
	 */
	void update(Car car);

	/**
	 * Adds a rate to the car.
	 * 
	 * @param idCar
	 * @param idRate
	 * @return 400 if the car has another rate in the dates given or 200 if
	 *         everything went fine.
	 */
	ResponseEntity<?> addRate(Integer idCar, Integer idRate);

	/**
	 * Deletes a car.
	 * 
	 * @param idCar
	 * @return 404 if the car is not found or 200 if deleted correctly.
	 */
	void delete(Integer idCar);
}
