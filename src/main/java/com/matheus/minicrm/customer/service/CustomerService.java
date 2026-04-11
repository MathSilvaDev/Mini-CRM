package com.matheus.minicrm.customer.service;

import com.matheus.minicrm.contact.dto.ContactResponse;
import com.matheus.minicrm.customer.dto.request.CustomerCreateRequest;
import com.matheus.minicrm.customer.dto.response.CustomerReponseWithContacts;
import com.matheus.minicrm.customer.dto.response.CustomerResponse;
import com.matheus.minicrm.customer.entity.Customer;
import com.matheus.minicrm.customer.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

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

    public List<CustomerReponseWithContacts> findAll(){

        return customerRepository.findAll()
                .stream()
                .map(this::toResponseWithContacts)
                .toList();
    }

    private CustomerResponse toResponse(Customer customer){
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail()
        );
    }

    private CustomerReponseWithContacts toResponseWithContacts(Customer customer){

        return new CustomerReponseWithContacts(
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
