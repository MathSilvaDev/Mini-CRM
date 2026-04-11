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

    @PostMapping("/create")
    public ResponseEntity<CustomerResponse> create(
            @Valid @RequestBody CustomerCreateRequest request){

        return ResponseEntity.ok(
                customerService.create(request)
        );
    }

    @GetMapping("/all")
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

}
