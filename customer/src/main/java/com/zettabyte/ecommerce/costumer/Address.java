package com.zettabyte.ecommerce.costumer;

import org.springframework.validation.annotation.Validated;

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
@Validated

public class Address {
	  private Long adressId;
	  private Long clientId;
	  private String street;
	  private String city;
	  private String state;
	  private String number;
	  private String complement;
	  private String postalCode;
}
