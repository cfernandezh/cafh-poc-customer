package com.cafh.poc.customer.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerKpiResponse {

    @JsonProperty("ageAvg")
    private Double ageAvg;

    @JsonProperty("ageStDev")
    private Double ageStDev;
}
