package com.atsistemas.rentService.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atsistemas.rentService.model.Rate;

public interface RateRepository extends JpaRepository<Rate, Integer> {
}
