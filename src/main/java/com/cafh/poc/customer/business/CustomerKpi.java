package com.cafh.poc.customer.business;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerKpi {

    private Double ageAverage;

    private Double ageStandardDeviation;

}
