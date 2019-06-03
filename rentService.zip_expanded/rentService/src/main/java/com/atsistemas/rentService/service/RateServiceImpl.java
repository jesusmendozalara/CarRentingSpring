package com.atsistemas.rentService.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atsistemas.rentService.dao.RateRepository;
import com.atsistemas.rentService.model.Rate;

@Service
public class RateServiceImpl implements RateService {
	@Autowired
	RateRepository rateRepository;
	@Autowired
	CarService carService;

	@Override
	public Rate create(Rate rate) {
		return rateRepository.save(rate);
	}

	@Override
	public Optional<Rate> findById(Integer id) {
		return rateRepository.findById(id);
	}
}
