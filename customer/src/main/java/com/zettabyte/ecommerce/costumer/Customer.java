package com.zettabyte.ecommerce.costumer;

import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.util.ArrayList;

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
	private ArrayList<Address> addresses;
	private Boolean idAdmin;
	private Boolean idActive;
	private Timestamp createdAt;
	private Timestamp updateddAt;
	
	
	
	
}
