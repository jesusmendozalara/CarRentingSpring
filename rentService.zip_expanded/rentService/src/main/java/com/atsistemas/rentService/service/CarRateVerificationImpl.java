package com.atsistemas.rentService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsistemas.rentService.model.Car;
import com.atsistemas.rentService.model.Rate;

@Service
public class CarRateVerificationImpl implements CarRateVerification {
	@Autowired
	DateValidator dateValidator;

	@Override
	public Boolean verifyCarRate(Car car, Rate newRate) {
		for (Rate rate : car.getRates()) {
			if (!dateValidator.validation(newRate.getStartDate(), newRate.getEndDate(), rate.getStartDate(),
					rate.getEndDate())) {
				return false;
			}
		}
		return true;
	}
}
