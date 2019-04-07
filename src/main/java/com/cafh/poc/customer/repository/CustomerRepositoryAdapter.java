package com.cafh.poc.customer.repository;

import com.cafh.poc.customer.business.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerRepositoryAdapter {

    public CustomerEntity convertDomainToEntity(Customer domain){
        return CustomerEntity.builder()
                .name(domain.getName())
                .lastName(domain.getLastName())
                .age(domain.getAge())
                .dateOfBirth(domain.getDateOfBirth())
                .build();
    }

}
