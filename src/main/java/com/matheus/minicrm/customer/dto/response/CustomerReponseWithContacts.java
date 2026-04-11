package com.matheus.minicrm.customer.dto.response;

import com.matheus.minicrm.contact.dto.ContactResponse;

import java.util.List;

public record CustomerReponseWithContacts(
        Long id,
        String name,
        String email,
        List<ContactResponse> contacts
) {}
