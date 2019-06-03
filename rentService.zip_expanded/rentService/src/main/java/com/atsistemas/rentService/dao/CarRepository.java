package com.atsistemas.rentService.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.atsistemas.rentService.model.Car;

public interface CarRepository extends JpaRepository<Car, Integer> {
	public Page<Car> findAll(Pageable page);
}
