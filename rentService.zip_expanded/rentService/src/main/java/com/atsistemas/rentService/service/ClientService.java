package com.atsistemas.rentService.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import com.atsistemas.rentService.model.Client;
import com.atsistemas.rentService.model.Rental;

public interface ClientService {
	/**
	 * Return a client if exists.
	 * 
	 * @param id
	 * @return
	 */
	Optional<Client> findById(Integer id);

	/**
	 * Return a list with all the clients or the clients searched for.
	 * 
	 * @return
	 */
	List<Client> findAll(Pageable page, String name);

	/**
	 * Returns a set with all rentals from a client.
	 * 
	 * @return
	 */
	Set<Rental> getAllRental(Integer idClient);
	
	/**
	 * Creates a client.
	 * 
	 * @param client
	 * @return
	 */
	Client create(Client client);

	/**
	 * Update a client.
	 * 
	 * @param client
	 */
	void update(Client client);

	/**
	 * Deletes the client with the id given.
	 * 
	 * @param idClient
	 */
	void delete(Integer idClient);
}
