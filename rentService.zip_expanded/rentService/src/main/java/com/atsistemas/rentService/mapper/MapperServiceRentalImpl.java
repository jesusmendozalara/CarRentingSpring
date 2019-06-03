package com.atsistemas.rentService.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atsistemas.rentService.dto.RentalDTO;
import com.atsistemas.rentService.model.Rental;

@Component
public class MapperServiceRentalImpl implements MapperServiceDate<RentalDTO, Rental> {
	@Autowired
	MapperServiceCarImpl mapperServiceCar;
	@Autowired
	MapperServiceClientImpl mapperServiceClient;
	
	@Override
	public RentalDTO mapToDto(Rental rental) {
		final RentalDTO rentalDto = new RentalDTO();
		rentalDto.setEndDate(rental.getEndDate().toString());
		rentalDto.setStartDate(rental.getStartDate().toString());
		rentalDto.setId(rental.getId());
		rentalDto.setPrice(rental.getPrice());
		rentalDto.setCar(mapperServiceCar.mapToDto(rental.getCar()));
		rentalDto.setClient(mapperServiceClient.mapToDto(rental.getClient()));
		return rentalDto;
	}

	@Override
	public Rental mapToEntity(RentalDTO rentalDto) throws ParseException {
		final Rental rental = new Rental();
		rental.setEndDate(new SimpleDateFormat("dd/MM/yyyy").parse(rentalDto.getEndDate()));
		rental.setStartDate(new SimpleDateFormat("dd/MM/yyyy").parse(rentalDto.getStartDate()));
		rental.setId(rentalDto.getId());
		rental.setPrice(rentalDto.getPrice());
		rental.setCar(mapperServiceCar.mapToEntity(rentalDto.getCar()));
		rental.setClient(mapperServiceClient.mapToEntity(rentalDto.getClient()));
		return rental;
	}
}
