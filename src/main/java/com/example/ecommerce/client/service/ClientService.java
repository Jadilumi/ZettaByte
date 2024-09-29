package com.example.ecommerce.client.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.ecommerce.client.DTO.ClientDTO;
import com.example.ecommerce.client.entity.Client;
import com.example.ecommerce.client.exception.CustomException;
import com.example.ecommerce.client.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientRepository clientRepository;

	public Client createClient(ClientDTO receivedClient) {
		Client newClient = new Client();
		BeanUtils.copyProperties(receivedClient, newClient);
		newClient.setCreatedAt(LocalDateTime.now());
		newClient.setUpdatedAt(LocalDateTime.now());
		newClient.setIsActive(true); // Define como ativo por padrão
		return clientRepository.save(newClient);
	}

	public void deleteClient(Long id) {
		Client client = clientRepository.findById(id)
				.orElseThrow(() -> new CustomException("Cliente not found", HttpStatus.NOT_FOUND));
		client.setIsActive(false); // Não excluir fisicamente, apenas desativar
		clientRepository.save(client);
	}

	public Optional<Client> getClientById(Long id) {
		return clientRepository.findById(id).map(client -> {
			client.setUpdatedAt(LocalDateTime.now());
			return client;
		});
	}

	public Page<Client> getClients(int page, int size) {
		return clientRepository.findAll(PageRequest.of(page, size));
	}

	public Client updateClient(Long id, ClientDTO receivedClient) {
		Client existingClient = clientRepository.findById(id)
				.orElseThrow(() -> new CustomException("Cliente not found", HttpStatus.NOT_FOUND));

		if (receivedClient.getName() != null) {
			existingClient.setName(receivedClient.getName());
		}

		if (receivedClient.getEmail() != null) {
			existingClient.setEmail(receivedClient.getEmail());
		}

		if (receivedClient.getIsAdmin() != null) {
			existingClient.setIsAdmin(receivedClient.getIsAdmin());
		}

		if (receivedClient.getIsActive() != null) {
			existingClient.setIsActive(receivedClient.getIsActive());
		}

		existingClient.setUpdatedAt(LocalDateTime.now());
		return clientRepository.save(existingClient);
	}
}
