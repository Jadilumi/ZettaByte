package com.dieldev.ecommerce.client.controller;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

import com.dieldev.ecommerce.client.DTO.ClientDTO;
import com.dieldev.ecommerce.client.entity.Client;
import com.dieldev.ecommerce.client.service.ClientService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clients")
public class ClientController {

	private final ClientService clientService;

	@PostMapping("/save")
	public ResponseEntity<Client> createClient(@RequestBody ClientDTO receivedClient) {
		return new ResponseEntity<>(clientService.createClient(receivedClient), HttpStatus.CREATED);
	}

	@DeleteMapping("/del/{id}")
	public ResponseEntity<String> deleteClient(@PathVariable Long id) {
		clientService.deleteClient(id);
		return new ResponseEntity<>("Cliente deletado com sucesso", HttpStatus.OK);
	}

	@PostMapping("/inactivate/{id}")
	public ResponseEntity<String> inactivateClient(@PathVariable Long id) {
		clientService.inactivateClient(id);
		return new ResponseEntity<>("Cliente inativado com sucesso", HttpStatus.GONE);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable Long id) {
		Optional<Client> optionalClient = clientService.getClientById(id);

		if (optionalClient.isPresent()) {
			return new ResponseEntity<>(optionalClient.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorna 404 se o cliente não for encontrado
		}
	}

	@GetMapping("/")
	public ResponseEntity<Page<Client>> getClients(@RequestParam int page, @RequestParam int size) {
		return new ResponseEntity<>(clientService.getClients(page, size), HttpStatus.OK);
	}

	@GetMapping("/exists/{id}")
	public ResponseEntity<Boolean> existsClientById(@PathVariable Long id) {
		boolean exists = clientService.getClientById(id).isPresent(); // Verifica se o cliente existe
		return new ResponseEntity<>(exists, HttpStatus.OK); // Retorna true ou false
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<Client> editClient(@RequestBody ClientDTO receivedClient, @PathVariable Long id) {
		return new ResponseEntity<>(clientService.updateClient(id, receivedClient), HttpStatus.ACCEPTED);
	}
}
