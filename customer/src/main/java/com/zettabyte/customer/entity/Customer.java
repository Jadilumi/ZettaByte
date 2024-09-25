package com.zettabyte.customer.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document

public class Customer {
	
	@Id
	private String id;
	private String name;
	private String email;
	private String password;
	private List<Address> addresses;
	private Boolean isAdmin;
	private Boolean isActive;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	
	
	
}
