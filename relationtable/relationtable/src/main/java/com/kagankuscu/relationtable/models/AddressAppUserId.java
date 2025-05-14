package com.kagankuscu.relationtable.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class AddressAppUserId implements Serializable {

    @Column(name = "address_id")
    private Long addressId;

    @Column(name = "user_id")
    private Long userId;
}
