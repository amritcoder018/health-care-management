package com.amritsolution.model.dto;

import lombok.Value;

@Value
public class Address {
    String addressLine1;
    String addressLine2;
    String city;
    String district;
    String state;
    String landmark;
    String pinCode;
}
