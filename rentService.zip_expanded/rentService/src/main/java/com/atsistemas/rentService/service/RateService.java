package com.atsistemas.rentService.service;

import java.util.Optional;

import com.atsistemas.rentService.model.Rate;

public interface RateService {
	/**
	 * Creates a new rate.
	 * 
	 * @param rate
	 * @throws Exception
	 */
	Rate create(Rate rate);

	/**
	 * Return a rate with the given id.
	 * 
	 * @param id
	 * @return
	 */
	Optional<Rate> findById(Integer id);
}
