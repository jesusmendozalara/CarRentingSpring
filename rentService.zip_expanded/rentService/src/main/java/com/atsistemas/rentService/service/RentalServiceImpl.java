package com.atsistemas.rentService.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atsistemas.rentService.dao.RentalRepository;
import com.atsistemas.rentService.model.Car;
import com.atsistemas.rentService.model.Client;
import com.atsistemas.rentService.model.Rental;

@Service
public class RentalServiceImpl implements RentalService {
	@Autowired
	RentalRepository rentalRepository;
	@Autowired
	CarService carService;
	@Autowired
	ClientService clientService;

	@Override
	public Optional<Rental> findById(Integer id) {
		return rentalRepository.findById(id);
	}

	@Override
	public List<Rental> findAll(Pageable page) {
		return rentalRepository.findAll(page).getContent();
	}

	@Override
	public Rental create(Rental rental) {
		Integer idCar = rental.getCar().getId();
		Car car = carService.findById(idCar).get();
		car.getRentals().add(rental);
		Integer idClient = rental.getClient().getId();
		Client client = clientService.findById(idClient).get();
		client.getRentals().add(rental);
		return rentalRepository.save(rental);
	}

	@Override
	public void update(Rental rental) {
		Rental dbRental = rentalRepository.findById(rental.getId()).get();
		dbRental.setCar(rental.getCar());
		dbRental.setClient(rental.getClient());
		dbRental.setEndDate(rental.getEndDate());
		dbRental.setStartDate(rental.getStartDate());
		dbRental.setPrice(rental.getPrice());
		rentalRepository.save(dbRental);
	}

	@Override
	public void delete(Integer idRental) {
		rentalRepository.deleteById(idRental);
	}

	@Override
	public List<Rental> getGroupedRentals(Date startDate, Date endDate) {
		return rentalRepository.getGroupedRentals(startDate, endDate);
	}

}
