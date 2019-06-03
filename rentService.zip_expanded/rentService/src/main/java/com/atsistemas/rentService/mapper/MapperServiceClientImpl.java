package com.atsistemas.rentService.mapper;

import org.springframework.stereotype.Component;

import com.atsistemas.rentService.dto.ClientDTO;
import com.atsistemas.rentService.model.Client;

@Component
public class MapperServiceClientImpl implements MapperService<ClientDTO, Client> {

	@Override
	public ClientDTO mapToDto(Client client) {
		ClientDTO clientdto = new ClientDTO();
		clientdto.setDni(client.getDni());
		clientdto.setId(client.getId());
		clientdto.setName(client.getName());
		return clientdto;
	}

	@Override
	public Client mapToEntity(ClientDTO clientdto) {
		Client client = new Client();
		client.setDni(clientdto.getDni());
		client.setId(clientdto.getId());
		client.setName(clientdto.getName());
		return client;
	}

}
