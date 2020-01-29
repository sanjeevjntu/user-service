package com.fordav.autonomous.userservice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
        Customer mock = Customer.builder().id(1L).vin("VIN1234").name("sanjeev").build();
        when(customerRepository.save(any(Customer.class))).thenReturn(mock);

        Customer actual = classToTest.create(mock);
        verify(customerRepository, times(1)).save(mock);
        Assertions.assertThat(actual).isEqualTo(mock);
    }
}
