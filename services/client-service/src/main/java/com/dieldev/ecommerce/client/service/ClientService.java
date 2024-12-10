package com.dieldev.ecommerce.client.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dieldev.ecommerce.client.DTO.AddressDTO;
import com.dieldev.ecommerce.client.DTO.ClientDTO;
import com.dieldev.ecommerce.client.entity.Address;
import com.dieldev.ecommerce.client.entity.Client;
import com.dieldev.ecommerce.client.exception.CustomException;
import com.dieldev.ecommerce.client.repository.AddressRepository;
import com.dieldev.ecommerce.client.repository.ClientRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService {

	private final ClientRepository clientRepository;
	private final AddressRepository addressRepository;

	public Client createClient(ClientDTO clientDTO) {
		// Converter DTO para entidade
		Client client = new Client();
		client.setName(clientDTO.getName());
		client.setEmail(clientDTO.getEmail());
		client.setPassword(clientDTO.getPassword());
		client.setIsAdmin(clientDTO.getIsAdmin());
		client.setIsActive(clientDTO.getIsActive());
		client.setCreatedAt(LocalDateTime.now());
		client.setUpdatedAt(LocalDateTime.now());

		// Salvar cliente primeiro
		Client savedClient = clientRepository.save(client);

		// Salvar endereços associados
		if (clientDTO.getAddresses() != null) {
			for (AddressDTO addressDTO : clientDTO.getAddresses()) {
				Address address = new Address();
				address.setStreet(addressDTO.getStreet());
				address.setCity(addressDTO.getCity());
				address.setState(addressDTO.getState());
				address.setNumber(addressDTO.getNumber());
				address.setComplement(addressDTO.getComplement());
				address.setPostalCode(addressDTO.getPostalCode());
				address.setClient(savedClient); // Associar o ID do cliente

				addressRepository.save(address); // Salvar o endereço
			}
		}

		return savedClient;
	}

	@Transactional // Assegura que a operação é realizada em uma transação
	public void deleteClient(UUID id) {
		if (clientRepository.existsById(id)) { // Verifica se o cliente existe
			clientRepository.deleteById(id); // Exclui o cliente
		} else {
			throw new EntityNotFoundException("Cliente não encontrado");
		}
	}

	@Transactional
	public void inactivateClient(UUID id) {
		Client client = clientRepository.findById(id)
				.orElseThrow(() -> new CustomException("Cliente not found", HttpStatus.NOT_FOUND));
		client.setIsActive(false); // Não excluir fisicamente, apenas desativa
		clientRepository.save(client);
	}

	public Optional<Client> getClientById(UUID id) {
		return clientRepository.findById(id).map(client -> {
			client.setUpdatedAt(LocalDateTime.now());
			return client;
		});
	}

	public Page<Client> getClients(int page, int size) {
		return clientRepository.findAll(PageRequest.of(page, size));
	}

	public Client updateClient(UUID id, ClientDTO receivedClient) {
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

		// Atualizar a lista de endereços
		if (receivedClient.getAddresses() != null) {
			for (AddressDTO addressDTO : receivedClient.getAddresses()) {
				if (addressDTO.getAddressId() != null) {
					// Se o addressId estiver presente, atualiza o endereço existente
					Optional<Address> existingAddressOpt = addressRepository.findById(addressDTO.getAddressId());
					if (existingAddressOpt.isPresent()) {
						Address existingAddress = existingAddressOpt.get();
						existingAddress.setStreet(addressDTO.getStreet());
						existingAddress.setCity(addressDTO.getCity());
						existingAddress.setState(addressDTO.getState());
						existingAddress.setNumber(addressDTO.getNumber());
						existingAddress.setComplement(addressDTO.getComplement());
						existingAddress.setPostalCode(addressDTO.getPostalCode());
						addressRepository.save(existingAddress);
					}
				} 
//					else {
//					// Se não houver addressId, crie um novo endereço
//					Address newAddress = new Address();
//					newAddress.setClientId(existingClient.getClientId()); // Certifique-se de que isso não seja nulo
//					newAddress.setStreet(addressDTO.getStreet());
//					newAddress.setCity(addressDTO.getCity());
//					newAddress.setState(addressDTO.getState());
//					newAddress.setNumber(addressDTO.getNumber());
//					newAddress.setComplement(addressDTO.getComplement());
//					newAddress.setPostalCode(addressDTO.getPostalCode());
//					addressRepository.save(newAddress);
//				}
			}
		}

		existingClient.setUpdatedAt(LocalDateTime.now());
		return clientRepository.save(existingClient);
	}

}
