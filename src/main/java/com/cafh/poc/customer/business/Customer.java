package com.cafh.poc.customer.business;

import lombok.*;

@Data
@Builder
public class Customer {

    private String id;

    private String name;

    private String lastName;

    private Integer age;

    private Long dateOfBirth;

    private Long estimatedDeathDate;
}
