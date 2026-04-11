package com.matheus.minicrm.contact.dto.request;

import com.matheus.minicrm.contact.enums.ContactType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContactCreateRequest(
        @NotNull
        ContactType type,

        @NotBlank
        String contactValue
) { }
