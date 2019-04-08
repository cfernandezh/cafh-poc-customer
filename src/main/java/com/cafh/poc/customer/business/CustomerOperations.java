package com.cafh.poc.customer.business;

import java.util.List;

public interface CustomerOperations {

    void createCustomer(Customer customer);

    CustomerKpi getCustomerKpi();

    List<Customer> getCustomerList();

}
