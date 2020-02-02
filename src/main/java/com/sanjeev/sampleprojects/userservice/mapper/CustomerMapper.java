package com.sanjeev.sampleprojects.userservice.mapper;

import com.sanjeev.sampleprojects.userservice.domain.Customer;
import com.sanjeev.sampleprojects.userservice.dto.CustomerDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toDomain(CustomerDto customerDto);
    CustomerDto toDto(Customer customer);
    List<Customer> toDomain(List<CustomerDto> customerDto);
    List<CustomerDto> toDto(List<Customer> customer);
}
