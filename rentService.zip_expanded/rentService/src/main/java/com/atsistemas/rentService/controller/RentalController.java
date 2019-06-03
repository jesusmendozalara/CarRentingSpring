package com.atsistemas.rentService.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.atsistemas.rentService.dto.RentalDTO;
import com.atsistemas.rentService.mapper.MapperServiceRentalImpl;
import com.atsistemas.rentService.model.Rental;
import com.atsistemas.rentService.service.RentalService;
import com.atsistemas.rentService.service.VerifyRental;

@RestController
@RequestMapping("/rental")
public class RentalController {
	@Autowired
	private RentalService rentalService;
	@Autowired
	private MapperServiceRentalImpl mapperService;
	@Autowired
	VerifyRental verifyRental;

	@GetMapping("/{idRental}")
	public ResponseEntity<RentalDTO> getRental(@PathVariable("idRental") Integer idRental) {
		Optional<Rental> rental = rentalService.findById(idRental);
		if (rental.isPresent()) {
			RentalDTO rentaldto = (mapperService.mapToDto(rental.get()));
			return ResponseEntity.ok(rentaldto);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public List<RentalDTO> getAllRental(
			@RequestParam(defaultValue = "0", value = "page", required = false) Integer page,
			@RequestParam(defaultValue = "5", value = "size", required = false) Integer size) {
		List<Rental> rentals = rentalService.findAll(PageRequest.of(page, size));
		List<RentalDTO> rentalsdto = new ArrayList<RentalDTO>();
		for (Rental rental : rentals) {
			rentalsdto.add(mapperService.mapToDto(rental));
		}
		return rentalsdto;
	}

	@PostMapping
	public ResponseEntity<RentalDTO> createRental(@RequestBody RentalDTO rentaldto) {
		Rental rental;
		try {
			rental = mapperService.mapToEntity(rentaldto);
			if (verifyRental.verify(rental)) {
				return ResponseEntity.ok(mapperService.mapToDto(rentalService.create(rental)));
			} else {
				return ResponseEntity.badRequest().build();
			}
		} catch (ParseException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PutMapping
	public ResponseEntity<?> updateRental(@RequestBody RentalDTO rentaldto) {
		Rental rental;
		try {
			rental = mapperService.mapToEntity(rentaldto);
			if (verifyRental.verify(rental)) {
				rentalService.update(rental);
				return ResponseEntity.ok().build();
			} else {
				return ResponseEntity.badRequest().build();
			}
		} catch (ParseException e) {
			return ResponseEntity.badRequest().build();
		}
	}

	@DeleteMapping("{idRental}")
	public ResponseEntity<?> deleteRental(@PathVariable Integer idRental) {
		if (rentalService.findById(idRental).isPresent()) {
			rentalService.delete(idRental);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
