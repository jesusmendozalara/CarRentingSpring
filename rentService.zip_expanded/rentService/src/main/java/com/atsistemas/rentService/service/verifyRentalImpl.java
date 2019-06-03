package com.atsistemas.rentService.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsistemas.rentService.model.Car;
import com.atsistemas.rentService.model.Client;
import com.atsistemas.rentService.model.Rental;

@Service
public class verifyRentalImpl implements VerifyRental {
	@Autowired
	DateValidator dateValidator;
	@Autowired
	CarService carService;
	@Autowired
	ClientService clientService;

	@Override
	public Boolean verify(Rental rental) {
		Optional<Car> car = carService.findById(rental.getCar().getId());
		Optional<Client> client = clientService.findById(rental.getClient().getId());

		// Comprobamos que exista el coche y el cliente
		if (!car.isPresent() || !client.isPresent()) {
			return false;
		}

		// Comprobamos que el coche no tenga otro alquiler en ese momento
		for (Rental rentalCar : carService.getAllRental(car.get().getId())) {
			if (!dateValidator.validation(rentalCar.getStartDate(), rentalCar.getEndDate(), rental.getStartDate(),
					rental.getEndDate()) && (rentalCar.getId() != rental.getId())) {
				return false;
			}
		}

		// Comprobamos que el cliente no tenga otro alquiler en ese momento
		for (Rental rentalClient : clientService.getAllRental(client.get().getId())) {
			if (!dateValidator.validation(rentalClient.getStartDate(), rentalClient.getEndDate(), rental.getStartDate(),
					rental.getEndDate()) && (rentalClient.getId() != rental.getId())) {
				return false;
			}
		}
		return true;
	}
}
