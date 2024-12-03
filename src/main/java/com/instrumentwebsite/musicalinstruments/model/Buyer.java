package com.instrumentwebsite.musicalinstruments.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BUYER")
public class Buyer extends Users {

    public Buyer() {
        setRole(Role.BUYER);
    }

    public Buyer(String firstName, String lastName, String email, String phoneNumber) {
        super(firstName, lastName, email, phoneNumber);
        setRole(Role.BUYER);
    }
}