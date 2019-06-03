package com.atsistemas.rentService.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atsistemas.rentService.dao.ClientRepository;
import com.atsistemas.rentService.model.Client;
import com.atsistemas.rentService.model.Rental;

@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	RentalService rentalService;

	@Override
	public Optional<Client> findById(Integer id) {
		return clientRepository.findById(id);
	}

	@Override
	public List<Client> findAll(Pageable page, String name) {
		return clientRepository.findByNameContaining(page, name).getContent();
	}

	public Set<Rental> getAllRental(Integer idCar) {
		return clientRepository.findById(idCar).get().getRentals();
	}

	@Override
	public Client create(Client client) {
		return clientRepository.save(client);
	}

	@Override
	public void update(Client client) {
		Client dbClient = clientRepository.findById(client.getId()).get();
		dbClient.setDni(client.getDni());
		dbClient.setName(client.getName());
		dbClient.setRentals(client.getRentals());
		clientRepository.save(dbClient);
	}

	@Override
	public void delete(Integer idClient) {
		clientRepository.deleteById(idClient);
	}
}
