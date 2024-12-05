package com.dieldev.ecommerce.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dieldev.ecommerce.client.entity.Address;

import java.util.List;
import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	List<Address> findByClientClientId(UUID clientId);
}
