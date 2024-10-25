package com.example.ecommerce.client.controller;

import com.example.ecommerce.client.entity.Address;
import com.example.ecommerce.client.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients/{clientId}/addresses")
public class AddressController {

	@Autowired
	private AddressService addressService;

	@GetMapping
	public List<Address> getAllAddresses(@PathVariable Long clientId) {
		return addressService.getAllAddresses(clientId);
	}

	@GetMapping("/{addressId}")
	public Address getAddress(@PathVariable Long clientId, @PathVariable Long addressId) {
		return addressService.getAddress(clientId, addressId);
	}

	@PostMapping
	public Address createAddress(@PathVariable Long clientId, @RequestBody Address address) {
		return addressService.createAddress(clientId, address);
	}

	@PutMapping("/{addressId}")
	public Address updateAddress(@PathVariable Long clientId, @PathVariable Long addressId,
			@RequestBody Address addressDetails) {
		return addressService.updateAddress(clientId, addressId, addressDetails);
	}

	@DeleteMapping("/{addressId}")
	public ResponseEntity<Void> deleteAddress(@PathVariable Long clientId, @PathVariable Long addressId) {
		addressService.deleteAddress(clientId, addressId);
		return ResponseEntity.noContent().build();
	}
}
