package com.atsistemas.rentService.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atsistemas.rentService.dto.ClientDTO;
import com.atsistemas.rentService.dto.RentalDTO;
import com.atsistemas.rentService.mapper.MapperServiceClientImpl;
import com.atsistemas.rentService.mapper.MapperServiceRentalImpl;
import com.atsistemas.rentService.model.Client;
import com.atsistemas.rentService.model.Rental;
import com.atsistemas.rentService.service.ClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
	@Autowired
	ClientService clientService;
	@Autowired
	MapperServiceClientImpl mapperService;
	@Autowired
	MapperServiceRentalImpl mapperServiceRental;

	@GetMapping("/{idClient}")
	public ResponseEntity<ClientDTO> getCar(@PathVariable("idClient") Integer idClient) {
		Optional<ClientDTO> clientdto = clientService.findById(idClient).map(mapperService::mapToDto);
		if (clientdto.isPresent()) {
			return ResponseEntity.ok(clientdto.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping
	public List<ClientDTO> getAllClient(
			@RequestParam(defaultValue = "0", value = "page", required = false) Integer page,
			@RequestParam(defaultValue = "5", value = "size", required = false) Integer size,
			@RequestParam(defaultValue = "", value = "name", required = false) String name) {
		List<Client> clients = clientService.findAll(PageRequest.of(page, size), name);
		List<ClientDTO> clientsdto = new ArrayList<ClientDTO>();
		for (Client client : clients) {
			clientsdto.add(mapperService.mapToDto(client));
		}
		return clientsdto;
	}

	@GetMapping("/{idClient}/rental")
	public Set<RentalDTO> getAllRental(@PathVariable Integer idClient) {
		Set<Rental> rentals = clientService.getAllRental(idClient);
		Set<RentalDTO> rentalsdto = new HashSet<RentalDTO>();
		for (Rental rental : rentals) {
			rentalsdto.add(mapperServiceRental.mapToDto(rental));
		}
		return rentalsdto;
	}

	@PostMapping
	public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientdto) {
		Client client = mapperService.mapToEntity(clientdto);
		return ResponseEntity.ok(mapperService.mapToDto(clientService.create(client)));
	}

	@PutMapping
	public ResponseEntity<?> updateClient(@RequestBody ClientDTO clientdto) {
		Client client = mapperService.mapToEntity(clientdto);
		clientService.update(client);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{idClient}")
	public ResponseEntity<?> deleteClient(@PathVariable Integer idClient) {
		if (clientService.findById(idClient).isPresent()) {
			clientService.delete(idClient);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
