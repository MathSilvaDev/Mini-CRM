package com.matheus.minicrm.contact.dto.response;

import com.matheus.minicrm.contact.enums.ContactType;

public record ContactResponse(
        Long id,
        ContactType type,
        String contactValue
) { }
