package com.instrumentwebsite.musicalinstruments.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends Users {

    public Admin() {
        setRole(Role.ADMIN);
    }

    public Admin(String firstName, String lastName, String email, String phoneNumber) {
        super(firstName, lastName, email, phoneNumber);
        setRole(Role.ADMIN);
    }
}