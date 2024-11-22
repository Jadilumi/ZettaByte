package com.dieldev.ecommerce.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dieldev.ecommerce.client.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}