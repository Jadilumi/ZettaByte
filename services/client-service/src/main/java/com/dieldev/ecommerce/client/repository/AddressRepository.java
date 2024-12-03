package com.dieldev.ecommerce.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dieldev.ecommerce.client.entity.Address;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	List<Address> findByClientClientId(Long clientId);
}
