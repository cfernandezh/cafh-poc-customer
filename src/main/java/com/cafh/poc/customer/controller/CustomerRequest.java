package com.cafh.poc.customer.controller;

import com.cafh.poc.customer.util.ValidAge;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@ValidAge
@Data
public class CustomerRequest {

    @NotNull
    private String name;

    @NotNull
    private String lastName;

    @NotNull
    @Min(value = 0)
    private Integer age;

    @NotNull
    @Pattern(regexp = "^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$")
    private String dateOfBirth;

}
