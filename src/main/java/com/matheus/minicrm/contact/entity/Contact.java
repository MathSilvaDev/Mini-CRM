package com.matheus.minicrm.contact.entity;

import com.matheus.minicrm.contact.enums.ContactType;
import com.matheus.minicrm.customer.entity.Customer;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contact")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContactType type;

    @Column(nullable = false, unique = true)
    private String contactValue;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    public Contact(ContactType type, String contactValue, Customer customer){
        this.type = type;
        this.contactValue = contactValue;
        this.customer = customer;
    }
}
