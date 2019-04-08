package com.cafh.poc.customer.repository;

import com.cafh.poc.customer.business.Customer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CustomerRepositoryAdapter {

    public CustomerEntity convertDomainToEntity(Customer domain){
        return Optional.
                ofNullable(domain)
                .map(domainObj -> CustomerEntity.builder()
                        .name(domainObj.getName())
                        .lastName(domainObj.getLastName())
                        .age(domainObj.getAge())
                        .dateOfBirth(domainObj.getDateOfBirth())
                        .build()).get();
    }

    public Customer convertEntityToDomain(CustomerEntity entity){
        return Optional.
                ofNullable(entity)
                .map(entityObj -> Customer.builder()
                        .id(entityObj.getId())
                        .name(entityObj.getName())
                        .lastName(entityObj.getLastName())
                        .age(entityObj.getAge())
                        .dateOfBirth(entityObj.getDateOfBirth())
                        .build()).get();
    }

    public List<Customer> convertEntityListToDomainList(List<CustomerEntity> entityList){
        return Optional.
                ofNullable(entityList)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(entity -> this.convertEntityToDomain(entity))
                .collect(Collectors.toList());
    }

}
