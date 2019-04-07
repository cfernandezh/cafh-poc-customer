package com.cafh.poc.customer.business.impl;

import com.cafh.poc.customer.business.Customer;
import com.cafh.poc.customer.business.CustomerOperations;
import com.cafh.poc.customer.repository.CustomerRepository;
import com.cafh.poc.customer.repository.CustomerRepositoryAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServices implements CustomerOperations {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerRepositoryAdapter customerRepositoryAdapter;

    @Override
    public void createCustomer(final Customer customer) {
       customerRepository.save(customerRepositoryAdapter.convertDomainToEntity(customer));
    }

}
