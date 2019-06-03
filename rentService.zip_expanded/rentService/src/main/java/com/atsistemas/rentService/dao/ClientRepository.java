package com.atsistemas.rentService.dao;

import com.atsistemas.rentService.model.Client;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
	public Page<Client> findByNameContaining(Pageable page, String name);
}
