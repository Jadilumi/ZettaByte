package com.dieldev.ecommerce.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.dieldev.ecommerce.client.entity.Address;
import com.dieldev.ecommerce.client.entity.Client;
import com.dieldev.ecommerce.client.repository.AddressRepository;
import com.dieldev.ecommerce.client.repository.ClientRepository;

import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ClientRepository clientRepository;

	public List<Address> getAllAddresses(Long clientId) {
		return addressRepository.findByClientClientId(clientId);
	}

	public Address getAddress(Long clientId, Long addressId) {
		return addressRepository.findById(addressId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado"));
	}

	public Address createAddress(Long clientId, Address address) {
		Client client = clientRepository.findById(clientId)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

		address.setClient(client);
		return addressRepository.save(address);
	}

	public Address updateAddress(Long clientId, Long addressId, Address addressDetails) {
		Address address = getAddress(clientId, addressId);

		address.setStreet(addressDetails.getStreet());
		address.setCity(addressDetails.getCity());
		address.setState(addressDetails.getState());
		address.setNumber(addressDetails.getNumber());
		address.setPostalCode(addressDetails.getPostalCode());

		return addressRepository.save(address);
	}

	public void deleteAddress(Long clientId, Long addressId) {
		Address address = getAddress(clientId, addressId);
		addressRepository.delete(address);
	}
}
