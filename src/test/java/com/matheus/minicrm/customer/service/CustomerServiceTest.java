package com.matheus.minicrm.customer.service;

import com.matheus.minicrm.contact.repository.ContactRepository;
import com.matheus.minicrm.customer.dto.request.CustomerCreateRequest;
import com.matheus.minicrm.customer.dto.response.CustomerResponse;
import com.matheus.minicrm.customer.entity.Customer;
import com.matheus.minicrm.customer.repository.CustomerRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private CustomerService customerService;

    @Nested
    public class Create{

        @Test
        void shouldCreateSuccessfully(){

            CustomerCreateRequest request = new CustomerCreateRequest(
                    "nameTest",
                    "test@email.com"
            );

            Customer customer = new Customer(request.name(), request.email());

            when(customerRepository.save(any(Customer.class)))
                    .thenReturn(customer);

            CustomerResponse response = customerService.create(request);

            assertEquals(request.name(), response.name());
            assertEquals(request.email(), response.email());

            verify(customerRepository).save(any(Customer.class));
        }
    }

}