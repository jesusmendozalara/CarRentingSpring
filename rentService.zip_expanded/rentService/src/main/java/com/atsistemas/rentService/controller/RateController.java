package com.atsistemas.rentService.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.rentService.dto.RateDTO;
import com.atsistemas.rentService.mapper.MapperServiceRateImpl;
import com.atsistemas.rentService.model.Rate;
import com.atsistemas.rentService.service.RateService;

@RestController
@RequestMapping("/rate")
public class RateController {
	@Autowired
	RateService rateService;
	@Autowired
	MapperServiceRateImpl mapperService;

	@PostMapping()
	public ResponseEntity<RateDTO> createRate(@RequestBody RateDTO rateDTO) {
		try {
			Rate rate = mapperService.mapToEntity(rateDTO);
			rateService.create(rate);
			return ResponseEntity.ok(mapperService.mapToDto(rateService.create(rate)));
		} catch (ParseException e) {
			return ResponseEntity.badRequest().build();// Dates format are wrong
		}
	}
}
