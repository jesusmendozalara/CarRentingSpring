package com.atsistemas.rentService.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.atsistemas.rentService.model.Rental;

public interface RentalRepository extends JpaRepository<Rental, Integer> {
	public Page<Rental> findAll(Pageable page);
	
	@Query("Select b from Rental b where b.startDate >= ?1 and b.endDate <= ?2")
	public List<Rental> getGroupedRentals(Date startDate, Date endDate);
}
