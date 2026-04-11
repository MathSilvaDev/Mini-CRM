package com.matheus.minicrm.customer.service;

import com.matheus.minicrm.contact.dto.request.ContactCreateRequest;
import com.matheus.minicrm.contact.dto.response.ContactResponse;
import com.matheus.minicrm.contact.entity.Contact;
import com.matheus.minicrm.contact.repository.ContactRepository;
import com.matheus.minicrm.customer.dto.request.CustomerCreateRequest;
import com.matheus.minicrm.customer.dto.response.CustomerResponseWithContacts;
import com.matheus.minicrm.customer.dto.response.CustomerResponse;
import com.matheus.minicrm.customer.entity.Customer;
import com.matheus.minicrm.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ContactRepository contactRepository;

    public CustomerResponse create(CustomerCreateRequest request){

        if (customerRepository.existsByEmail(request.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        Customer customer = new Customer(
                request.name(),
                request.email()
        );

        customerRepository.save(customer);

        return toResponse(customer);
    }

    public List<CustomerResponseWithContacts> findAll(){

        return customerRepository.findAll()
                .stream()
                .map(this::toResponseWithContacts)
                .toList();
    }

    @Transactional
    public CustomerResponseWithContacts addContactById(Long id, ContactCreateRequest request){

        Customer customer = findCustomerById(id);

        Contact contact = new Contact(
                request.type(),
                request.contactValue(),
                customer
        );

        contactRepository.save(contact);

        customer.addContact(contact);

        return toResponseWithContacts(customer);
    }

    public CustomerResponseWithContacts findById(Long id){
        Customer customer = findCustomerById(id);

        return toResponseWithContacts(customer);
    }

    private Customer findCustomerById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private CustomerResponse toResponse(Customer customer){
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail()
        );
    }

    private CustomerResponseWithContacts toResponseWithContacts(Customer customer){
        return new CustomerResponseWithContacts(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),

                customer.getContacts().stream()
                        .map(c -> new ContactResponse(
                                c.getId(),
                                c.getType(),
                                c.getContactValue()
                        )).toList()
        );
    }
}
