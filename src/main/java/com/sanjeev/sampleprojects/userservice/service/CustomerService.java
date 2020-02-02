package com.sanjeev.sampleprojects.userservice.service;

import com.sanjeev.sampleprojects.userservice.domain.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer create(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer update(Customer customer) {
        Customer customerToBeUpdated = findById(customer.getId().toString());
        customerToBeUpdated.setName(customer.getName());
        customerToBeUpdated.setVin(customer.getVin());
        return customerRepository.save(customerToBeUpdated);
    }

    public Customer findById(String id) {
        return customerRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new CustomerNotFoundException("customer not found"));
    }

    public void deleteById(String uuid) {
        customerRepository.deleteById(UUID.fromString(uuid));
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}
