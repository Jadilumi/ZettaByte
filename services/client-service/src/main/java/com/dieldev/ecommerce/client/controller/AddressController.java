package com.dieldev.ecommerce.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dieldev.ecommerce.client.entity.Address;
import com.dieldev.ecommerce.client.service.AddressService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients/{clientId}/addresses")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@GetMapping
	public List<Address> getAllAddresses(@PathVariable UUID clientId) {
		return addressService.getAllAddresses(clientId);
	}

	@GetMapping("/{addressId}")
	public Address getAddress(@PathVariable UUID clientId, @PathVariable Long addressId) {
		return addressService.getAddress(clientId, addressId);
	}

	@PostMapping
	public Address createAddress(@PathVariable UUID clientId, @RequestBody Address address) {
		return addressService.createAddress(clientId, address);
	}

	@PutMapping("/{addressId}")
	public Address updateAddress(@PathVariable UUID clientId, @PathVariable Long addressId,
			@RequestBody Address addressDetails) {
		return addressService.updateAddress(clientId, addressId, addressDetails);
	}

	@DeleteMapping("/{addressId}")
	public ResponseEntity<Void> deleteAddress(@PathVariable UUID clientId, @PathVariable Long addressId) {
		addressService.deleteAddress(clientId, addressId);
		return ResponseEntity.noContent().build();
	}
}
