package com.fordav.autonomous.userservice;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository classToTest;

    @Autowired
    private TestEntityManager entityManager;

    private Customer mock;
    @BeforeEach
    void setUp() {
        Customer mock = Customer.builder().vin("VIN1234").name("sanjeev").build();
        entityManager.persist(mock);
    }

    @AfterEach
    void tearDown() {
        classToTest.deleteAll();
    }

    @Test
    @DisplayName("should Success CreationOfCustomer")
    void shouldSuccessCreationOfCustomer() {
        Customer actual = classToTest.save(mock);
        Assertions.assertThat(actual.getId()).isEqualTo(mock.getId());
        Assertions.assertThat(actual).isEqualTo(mock);
    }
}