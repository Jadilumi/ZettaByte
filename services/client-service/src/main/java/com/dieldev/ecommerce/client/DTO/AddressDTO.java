package com.dieldev.ecommerce.client.DTO;

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
public class AddressDTO {
    private Long addressId;      // ID do endereço
    private Long clientId;       // ID do cliente associado ao endereço
    private String street;       // Rua
    private String city;         // Cidade
    private String state;        // Estado
    private String number;       // Número
    private String complement;   // Complemento
    private String postalCode;   // Código Postal
}