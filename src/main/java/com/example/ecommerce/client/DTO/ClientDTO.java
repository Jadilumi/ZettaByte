package com.example.ecommerce.client.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ClientDTO {
    private Long id;  						// O ID do cliente
    private String name;  					// Nome do cliente
    private String email;  					// Email do cliente
    private String password;                // Senha do cliente
    private List<AddressDTO> addresses;  	// Lista de endereços do cliente
    private Boolean isAdmin;  				// Indica se o cliente é administrador
    private Boolean isActive;  				// Indica se o cliente está ativo
    private LocalDateTime createdAt;  		// Data de criação do cliente
    private LocalDateTime updatedAt;  		// Data de atualização do cliente
}