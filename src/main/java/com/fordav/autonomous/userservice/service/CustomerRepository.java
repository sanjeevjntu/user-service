package com.fordav.autonomous.userservice.service;

import com.fordav.autonomous.userservice.controller.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
