package com.atsistemas.rentService.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.rentService.dto.CarDTO;
import com.atsistemas.rentService.dto.RateDTO;
import com.atsistemas.rentService.dto.RentalDTO;
import com.atsistemas.rentService.mapper.MapperServiceCarImpl;
import com.atsistemas.rentService.mapper.MapperServiceRateImpl;
import com.atsistemas.rentService.mapper.MapperServiceRentalImpl;
import com.atsistemas.rentService.model.Car;
import com.atsistemas.rentService.model.Rate;
import com.atsistemas.rentService.model.Rental;
import com.atsistemas.rentService.service.CarService;

@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
	CarService carService;
	@Autowired
	MapperServiceCarImpl mapperService;
	@Autowired
	MapperServiceRentalImpl mapperServiceRental;
	@Autowired
	MapperServiceRateImpl mapperServiceRate;

	@GetMapping("/{idCar}")
	public ResponseEntity<CarDTO> getCar(@PathVariable("idCar") Integer idCar) {
		Optional<CarDTO> cardto = carService.findById(idCar).map(mapperService::mapToDto);
		if (cardto.isPresent()) {
			return ResponseEntity.ok(cardto.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public List<CarDTO> getAllCar(@RequestParam(defaultValue = "0", value = "page", required = false) Integer page,
			@RequestParam(defaultValue = "5", value = "size", required = false) Integer size) {
		List<Car> cars = carService.findAll(PageRequest.of(page, size));
		List<CarDTO> carsdto = new ArrayList<CarDTO>();
		for (Car car : cars) {
			carsdto.add(mapperService.mapToDto(car));
		}
		return carsdto;
	}

	@GetMapping("/betterCar")
	public ResponseEntity<CarDTO> getBetterCar(
			@RequestParam(defaultValue = "01/01/2000", value = "startDate", required = false) String startDate,
			@RequestParam(defaultValue = "01/01/2100", value = "endDate", required = false) String endDate)
			throws ParseException {
		Optional<Car> car = carService.getBetterCar(new SimpleDateFormat("dd/MM/yyyy").parse(startDate),
				new SimpleDateFormat("dd/MM/yyyy").parse(endDate));
		if (car.isPresent()) {
			return ResponseEntity.ok(mapperService.mapToDto(car.get()));
		} else {
			return ResponseEntity.badRequest().build();
		}
	}

	@GetMapping("/{idCar}/rental")
	public Set<RentalDTO> getAllRental(@PathVariable Integer idCar) {
		Set<Rental> rentals = carService.getAllRental(idCar);
		Set<RentalDTO> rentalsdto = new HashSet<RentalDTO>();
		for (Rental rental : rentals) {
			rentalsdto.add(mapperServiceRental.mapToDto(rental));
		}
		return rentalsdto;
	}

	@GetMapping("/{idCar}/rate")
	public Set<RateDTO> getAllRate(@PathVariable Integer idCar) {
		Set<Rate> rates = carService.getAllRate(idCar);
		Set<RateDTO> ratesdto = new HashSet<RateDTO>();
		for (Rate rate : rates) {
			ratesdto.add(mapperServiceRate.mapToDto(rate));
		}
		return ratesdto;
	}

	@PostMapping
	public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO cardto) {
		Car car = mapperService.mapToEntity(cardto);
		return ResponseEntity.ok(mapperService.mapToDto(carService.create(car)));
	}

	@PutMapping
	public ResponseEntity<?> updateCar(@RequestBody CarDTO cardto) {
		Car car = mapperService.mapToEntity(cardto);
		carService.update(car);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{idCar}/rate/{idRate}")
	public ResponseEntity<?> addRate(@PathVariable Integer idCar, @PathVariable Integer idRate) {
		return carService.addRate(idCar, idRate);
	}

	@DeleteMapping("/{idCar}")
	public ResponseEntity<?> deleteCar(@PathVariable Integer idCar) {
		if (carService.findById(idCar).isPresent()) {
			carService.delete(idCar);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
