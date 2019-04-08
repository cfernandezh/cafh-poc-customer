package com.cafh.poc.customer.business.impl;

import com.cafh.poc.customer.business.Customer;
import com.cafh.poc.customer.business.CustomerKpi;
import com.cafh.poc.customer.business.CustomerOperations;
import com.cafh.poc.customer.config.ApplicationProperties;
import com.cafh.poc.customer.repository.CustomerRepository;
import com.cafh.poc.customer.repository.CustomerRepositoryAdapter;
import com.cafh.poc.customer.util.StatisticsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

@Service
public class CustomerServices implements CustomerOperations {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerRepositoryAdapter customerRepositoryAdapter;

    @Autowired
    private StatisticsUtil statisticsUtil;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Override
    public void createCustomer(final Customer customer) {
       customerRepository.save(
               customerRepositoryAdapter.convertDomainToEntity(customer));
    }

    @Override
    public CustomerKpi getCustomerKpi() {
        return getStatistics(
                customerRepositoryAdapter.convertEntityListToDomainList(
                        customerRepository.findAll()));
    }

    @Override
    public List<Customer> getCustomerList() {
        return customerRepositoryAdapter.convertEntityListToDomainList(
                customerRepository.findAll())
                .stream()
                .map(customer -> {
                    customer.setEstimatedDeathDate(
                            LocalDateTime.ofInstant(
                                     Instant.ofEpochMilli(
                                                    customer.getDateOfBirth().longValue())
                                    , TimeZone.getDefault().toZoneId())
                                    .plusYears(applicationProperties.getLifeExpectancy())
                                    .atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
                    return customer;
                })
                .collect(Collectors.toList());
    }

    private CustomerKpi getStatistics(List<Customer> customerList) {
        return CustomerKpi.builder()
                .ageAverage(
                        statisticsUtil.getAverage(
                                customerList
                                        .stream()
                                        .map(customer -> customer.getAge())
                                        .collect(Collectors.toList())))
                .ageStandardDeviation(
                        (applicationProperties.getMinDataForStatistics().compareTo(customerList.size()) <= 0)?
                        statisticsUtil.getStDev(customerList
                                .stream()
                                .map(customer -> customer.getAge())
                                .collect(Collectors.toList())):null)
                .build();
    }

}
