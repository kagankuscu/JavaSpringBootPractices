package com.kagankuscu.relationtable.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class AddressAppUser {

    @EmbeddedId
    private AddressAppUserId id;

    @ManyToOne
    @MapsId("addressId")
    private Address address;

    @ManyToOne
    @MapsId("userId")
    private AppUser user;
}
