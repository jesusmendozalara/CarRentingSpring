package com.atsistemas.rentService.service;

import com.atsistemas.rentService.model.Rental;

public interface VerifyRental {
	/**
	 * Verifica que el cliente no tenga otro alquiler en esa fecha y que el coche no
	 * está alquilado en esa fecha
	 * 
	 * @param rental
	 * @param client
	 * @param car
	 * @return true if verified
	 */
	Boolean verify(Rental rental);
}
