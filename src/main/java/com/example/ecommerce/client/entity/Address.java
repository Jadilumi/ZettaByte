package com.example.ecommerce.client.entity;

import jakarta.persistence.*;
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
@Entity
@Table(name = "addresses")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Long addressId;

	@ManyToOne
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;

	@Column(nullable = false)
	private String street;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String state;

	@Column(nullable = false)
	private String number;

	@Column(name = "complement", nullable = true)
	private String complement;
	
	@Column(name = "postal_code", nullable = false)
	private String postalCode;
}
