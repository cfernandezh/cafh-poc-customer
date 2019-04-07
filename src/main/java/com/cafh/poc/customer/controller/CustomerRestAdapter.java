package com.cafh.poc.customer.controller;

import com.cafh.poc.customer.business.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerRestAdapter {

    public Customer convertRequestToDomain(CustomerRequest request) {
        return Customer.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .age(request.getAge())
                .dateOfBirth(request.getDateOfBirth())
                .build();
    }

}
