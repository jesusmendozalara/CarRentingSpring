package com.atsistemas.rentService.mapper;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

import com.atsistemas.rentService.dto.RateDTO;
import com.atsistemas.rentService.model.Rate;

@Component
public class MapperServiceRateImpl implements MapperServiceDate<RateDTO, Rate> {

	@Override
	public RateDTO mapToDto(Rate rate) {
		final RateDTO rateDto = new RateDTO();
		rateDto.setEndDate(rate.getEndDate().toString());
		rateDto.setStartDate(rate.getStartDate().toString());
		rateDto.setId(rate.getId());
		rateDto.setPrice(rate.getPrice());
		return rateDto;
	}

	@Override
	public Rate mapToEntity(RateDTO rateDto) throws ParseException {
		final Rate rate = new Rate();
		rate.setEndDate(new SimpleDateFormat("dd/MM/yyyy").parse(rateDto.getEndDate()));
		rate.setStartDate(new SimpleDateFormat("dd/MM/yyyy").parse(rateDto.getStartDate()));
		rate.setId(rateDto.getId());
		rate.setPrice(rateDto.getPrice());
		return rate;
	}
}
