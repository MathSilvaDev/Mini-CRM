package com.matheus.minicrm.contact.dto.request;

import com.matheus.minicrm.contact.enums.ContactType;

public record ContactCreateRequest(
        ContactType type,
        String contactValue
) { }
