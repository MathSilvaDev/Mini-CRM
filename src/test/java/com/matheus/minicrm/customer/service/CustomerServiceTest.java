package com.matheus.minicrm.customer.service;

import com.matheus.minicrm.contact.dto.request.ContactCreateRequest;
import com.matheus.minicrm.contact.entity.Contact;
import com.matheus.minicrm.contact.enums.ContactType;
import com.matheus.minicrm.contact.repository.ContactRepository;
import com.matheus.minicrm.customer.dto.request.CustomerCreateRequest;
import com.matheus.minicrm.customer.dto.response.CustomerResponse;
import com.matheus.minicrm.customer.dto.response.CustomerResponseWithContacts;
import com.matheus.minicrm.customer.entity.Customer;
import com.matheus.minicrm.customer.repository.CustomerRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        @Test
        void shouldThrowConflictError(){

            CustomerCreateRequest request = new CustomerCreateRequest(
                    "nameTest",
                    "test@email.com"
            );

            when(customerRepository.existsByEmail(request.email()))
                    .thenReturn(true);

            assertThrows(ResponseStatusException.class,
                    () -> customerService.create(request));
        }
    }

    @Nested
    public class FindAll{

        @Test
        void shouldFindAllSuccessfully(){

            List<Customer> customers = generateCustomers(2);

            when(customerRepository.findAll())
                    .thenReturn(customers);

            List<CustomerResponseWithContacts> response = customerService.findAll();

            assertEquals(customers.size(), response.size());
        }

        private List<Customer> generateCustomers(int num){

            List<Customer> customers = new ArrayList<>();

            for(int i = 0; i < num; i ++){
                int count = i + 1;

                Customer customer = new Customer(
                        "nameTest-" + count,
                        String.format("test%d@email.com", count)
                );

                customers.add(customer);
            }

            return customers;
        }
    }

    @Nested
    public class AddContactById{

        @Test
        void shouldCreateContactSuccessfully(){

            Customer customer = new Customer(
                    "nameTest",
                    "test@email.com"
            );

            ContactCreateRequest request = new ContactCreateRequest(
                    ContactType.EMAIL,
                    "test@email.com"
            );

            Contact contact = new Contact(
                    request.type(),
                    request.contactValue(),
                    customer
            );

            when(customerRepository.findById(customer.getId()))
                    .thenReturn(Optional.of(customer));

            when(contactRepository.save(any(Contact.class)))
                    .thenReturn(contact);

            CustomerResponseWithContacts response =
                    customerService.addContactById(customer.getId(), request);

            assertEquals(customer.getContacts().size(), response.contacts().size());
            assertEquals(customer.getEmail(), response.email());

            verify(contactRepository).save(any(Contact.class));
            verify(customerRepository).findById(customer.getId());
        }
    }

    @Nested class FindById{

        @Test
        void shouldFindByIdSuccessfully(){

            Customer customer = new Customer(
                    "nameTest",
                    "test@email.com"
            );

            when(customerRepository.findById(customer.getId()))
                    .thenReturn(Optional.of(customer));

            CustomerResponseWithContacts response =
                    customerService.findById(customer.getId());

            assertEquals(customer.getContacts().size(), response.contacts().size());
            assertEquals(customer.getEmail(), response.email());

            verify(customerRepository).findById(customer.getId());
        }
    }
}