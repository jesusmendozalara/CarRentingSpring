package com.atsistemas.rentService.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.atsistemas.rentService.model.Rental;

public interface RentalService {
	/**
	 * Returns a rental if exists.
	 * 
	 * @param id
	 * @return
	 */
	Optional<Rental> findById(Integer id);

	/**
	 * Returns a list with the rentals.
	 * 
	 * @return
	 */
	List<Rental> findAll(Pageable page);

	/**
	 * Creates a rental.
	 * 
	 * @param rental
	 * @return
	 */
	Rental create(Rental rental);

	/**
	 * Updates a rental.
	 * 
	 * @param rental
	 */
	void update(Rental rental);

	/**
	 * Deletes a rental.
	 * 
	 * @param idRental
	 */
	void delete(Integer idRental);

	/**
	 * Returns a list of rental between the dates grouped by car
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Rental> getGroupedRentals(Date startDate, Date endDate);
}