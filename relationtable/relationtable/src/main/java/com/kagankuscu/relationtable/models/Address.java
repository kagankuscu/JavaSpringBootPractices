package com.kagankuscu.relationtable.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String postalcode;

//    @ManyToMany(mappedBy = "addresses")
//    private Set<AppUser> appUsers = new HashSet<>();
    @OneToMany(mappedBy = "address")
    private Set<AddressAppUser> addressAppUsers;
}
