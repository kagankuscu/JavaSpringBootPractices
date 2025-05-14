package com.kagankuscu.relationtable.controllers;

import com.kagankuscu.relationtable.models.Address;
import com.kagankuscu.relationtable.models.AppUser;
import com.kagankuscu.relationtable.repositoroies.AddressRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/addresses")
public class AddressController {
    private AddressRepository addressRepository;

    @PostMapping("/create")
    public Address create(@RequestBody Address address) {
        return addressRepository.save(address);
    }

    @GetMapping("/list")
    public List<Address> list() {
        return addressRepository.findAll();
    }
}
