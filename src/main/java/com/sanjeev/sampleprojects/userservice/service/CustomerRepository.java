package com.sanjeev.sampleprojects.userservice.service;

import com.sanjeev.sampleprojects.userservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
