package com.cafh.poc.customer.controller;

import com.cafh.poc.customer.business.Customer;
import com.cafh.poc.customer.business.CustomerKpi;
import com.cafh.poc.customer.config.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CustomerRestAdapter {

    @Autowired
    private ApplicationProperties applicationProperties;

    public Customer convertRequestToDomain(CustomerRequest request) {
        return Optional.
                ofNullable(request)
                .map(requestObj -> {
                    Customer customer = Customer.builder()
                                .name(requestObj.getName())
                                .lastName(requestObj.getLastName())
                                .age(requestObj.getAge())
                                .build();
                    try {
                        customer.setDateOfBirth(
                                new SimpleDateFormat(applicationProperties.getDateFormat())
                                        .parse(requestObj.getDateOfBirth()).getTime());
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return customer;
                }).get();
    }

    public CustomerResponse convertDomainToResponse(Customer domain) {
        return Optional.
                ofNullable(domain)
                .map(domainObj -> CustomerResponse.builder()
                        .name(domainObj.getName())
                        .lastName(domainObj.getLastName())
                        .age(domainObj.getAge())
                        .dateOfBirth(domainObj.getDateOfBirth())
                        .dateOfBirthFormatted(
                                new SimpleDateFormat(applicationProperties.getDateFormat())
                                        .format(domainObj.getDateOfBirth()))
                        .estimatedDeathDate(domainObj.getEstimatedDeathDate())
                        .estimatedDeathDateFormatted(
                                new SimpleDateFormat(applicationProperties.getDateFormat())
                                .format(domainObj.getEstimatedDeathDate())
                        )
                        .build()).get();
    }

    public List<CustomerResponse> convertDomainListToResponseList(List<Customer> domainList){
        return Optional.
                ofNullable(domainList)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(domain -> this.convertDomainToResponse(domain))
                .collect(Collectors.toList());
    }

    public CustomerKpiResponse convertKpiToResponse(CustomerKpi kpi) {
        return Optional.
                ofNullable(kpi)
                .map(kpiObj -> CustomerKpiResponse.builder()
                        .ageAvg(kpiObj.getAgeAverage())
                        .ageStDev(kpiObj.getAgeStandardDeviation())
                        .build()).get();
    }



}
