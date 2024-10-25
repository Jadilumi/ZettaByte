package com.example.ecommerce.client.entity;

import java.time.LocalDateTime;
import java.util.List;

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
@Table(name = "clients")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long clientId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	private List<Address> addresses;

	@Column(nullable = false)
	private Boolean isAdmin;

	@Column(nullable = false)
	private Boolean isActive;

	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;

	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt;

//	@ManyToOne
//	@JoinColumn(name = "client_id", nullable = false)
//	private Client client;
}
