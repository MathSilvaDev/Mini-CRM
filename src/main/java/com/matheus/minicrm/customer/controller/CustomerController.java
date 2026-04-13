package com.matheus.minicrm.customer.controller;

import com.matheus.minicrm.contact.dto.request.ContactCreateRequest;
import com.matheus.minicrm.contact.dto.response.ContactResponse;
import com.matheus.minicrm.customer.dto.request.CustomerCreateRequest;
import com.matheus.minicrm.customer.dto.response.CustomerResponseWithContacts;
import com.matheus.minicrm.customer.dto.response.CustomerResponse;
import com.matheus.minicrm.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> create(
            @Valid @RequestBody CustomerCreateRequest request){

        return ResponseEntity.ok(
                customerService.create(request)
        );
    }

    @GetMapping
    public ResponseEntity<List<CustomerResponseWithContacts>> findAll(){

        return ResponseEntity.ok(
                customerService.findAll()
        );
    }

    @PostMapping("/{id}/add-contact")
    public ResponseEntity<CustomerResponseWithContacts> addContactById(
            @PathVariable Long id,
            @Valid @RequestBody ContactCreateRequest request){

        return ResponseEntity.ok(
                customerService.addContactById(id, request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponseWithContacts> findById(
            @PathVariable Long id){

        return ResponseEntity.ok(
                customerService.findById(id)
        );
    }

}
