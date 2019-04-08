package com.cafh.poc.customer.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerResponse {

    private String name;

    private String lastName;

    private Integer age;

    @JsonIgnore
    private Long dateOfBirth;

    private String dateOfBirthFormatted;

    @JsonIgnore
    private Long estimatedDeathDate;

    private String estimatedDeathDateFormatted;

}
