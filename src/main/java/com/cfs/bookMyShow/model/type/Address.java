package com.cfs.bookMyShow.model.type;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String street;
    private String city;
    private String state;
    private String pinCode;
    private String country;
}
