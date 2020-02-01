package com.sanjeev.sampleprojects.userservice.controller;

import com.sanjeev.sampleprojects.userservice.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Customer-Service", description = "the Customer API")
@Validated
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Operation(summary = "Add a new Customer", description = "Add a new Customer")
    @PostMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        return new ResponseEntity<>(customerService.create(customer), HttpStatus.CREATED);
    }

    @Operation(summary = "Get All Customer", description = "Get all Customer")
    @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Customer>> createCustomer() {
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Update a new Customer", description = "Update a new Customer")
    @PutMapping(value = "/customers/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer,
                                           @Size(min = 36, max = 36, message = "uuid must be 36 characters")
                                           @PathVariable String uuid) {
        customer.setId(UUID.fromString(uuid));
        return new ResponseEntity<>(customerService.update(customer), HttpStatus.OK);
    }

    @Operation(summary = "Get Customer", description = "Get Customer")
    @GetMapping(value = "/customers/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> getCustomer(@Size(min = 36, max = 36, message = "uuid must be 36 characters")
                                           @PathVariable String uuid) {
        return new ResponseEntity<>(customerService.findById(uuid), HttpStatus.OK);
    }

    @Operation(summary = "Delete Customer", description = "Delete Customer")
    @DeleteMapping(value = "/customers/{uuid}")
    public void deleteCustomer(@Size(min = 36, max = 36, message = "uuid must be 36 characters")
                                           @PathVariable String uuid) {
        customerService.deleteById(uuid);
    }
}
