package com.atsistemas.rentService.service;

import com.atsistemas.rentService.model.Car;
import com.atsistemas.rentService.model.Rate;

public interface CarRateVerification {
	/**
	 * Dado un coche y una tarifa, comprueba que el coche no tiene otra tarifa
	 * asignada en el el intervalo de tiempo de la nueva tarifa.
	 * 
	 * @param car
	 * @param rental
	 * @return
	 */
	Boolean verifyCarRate(Car car, Rate newRate);
}
