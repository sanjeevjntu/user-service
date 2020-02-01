package com.sanjeev.sampleprojects.userservice;

import com.sanjeev.sampleprojects.userservice.controller.Customer;
import com.sanjeev.sampleprojects.userservice.service.CustomerRepository;
import com.sanjeev.sampleprojects.userservice.service.CustomerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {

    @InjectMocks
    private CustomerService classToTest;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    public void shouldSuccessfullyCreateACustomer() {
        Customer mock = Customer.builder().id(UUID.randomUUID()).vin("VIN1234").name("sanjeev").build();
        when(customerRepository.save(any(Customer.class))).thenReturn(mock);

        Customer actual = classToTest.create(mock);
        verify(customerRepository, times(1)).save(mock);
        Assertions.assertThat(actual).isEqualTo(mock);
    }
}
