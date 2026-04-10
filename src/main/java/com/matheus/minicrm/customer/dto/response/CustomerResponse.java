package com.matheus.minicrm.customer.dto.response;

public record CustomerResponse(
        Long id,
        String name,
        String email
) { }
