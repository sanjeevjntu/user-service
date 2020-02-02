package com.sanjeev.sampleprojects.userservice.controller;

import com.sanjeev.sampleprojects.userservice.domain.Customer;
import com.sanjeev.sampleprojects.userservice.dto.CustomerDto;
import com.sanjeev.sampleprojects.userservice.mapper.CustomerMapper;
import com.sanjeev.sampleprojects.userservice.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @Operation(summary = "Add a new Customer", description = "Add a new Customer")
    @PostMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        Customer customer = customerService.create(customerMapper.toDomain(customerDto));
        return new ResponseEntity<>(customerMapper.toDto(customer), HttpStatus.CREATED);
    }

    @Operation(summary = "Get All Customer", description = "Get all Customer")
    @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDto>> createCustomer() {
        List<CustomerDto> customerDtoList = customerMapper.toDto(customerService.findAll());
        return new ResponseEntity<>(customerDtoList, HttpStatus.OK);
    }

    @Operation(summary = "Update a new Customer", description = "Update a new Customer")
    @PutMapping(value = "/customers/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody CustomerDto customerDto,
                                                   @Size(min = 36, max = 36, message = "uuid must be 36 characters")
                                                   @PathVariable String uuid) {
        customerDto.setId(UUID.fromString(uuid));
        Customer updatedCustomer = customerService.update(customerMapper.toDomain(customerDto));
        return new ResponseEntity<>(customerMapper.toDto(updatedCustomer), HttpStatus.OK);
    }

    @Operation(summary = "Get Customer", description = "Get Customer")
    @GetMapping(value = "/customers/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> getCustomer(@Size(min = 36, max = 36, message = "uuid must be 36 characters")
                                                @PathVariable String uuid) {
        Customer customer = customerService.findById(uuid);
        return new ResponseEntity<>(customerMapper.toDto(customer), HttpStatus.OK);
    }

    @Operation(summary = "Delete Customer", description = "Delete Customer")
    @DeleteMapping(value = "/customers/{uuid}")
    public void deleteCustomer(@Size(min = 36, max = 36, message = "uuid must be 36 characters")
                               @PathVariable String uuid) {
        customerService.deleteById(uuid);
    }
}
