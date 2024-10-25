package com.example.ecommerce.client.repository;

import com.example.ecommerce.client.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	List<Address> findByClientClientId(Long clientId);
}
