package com.cafh.poc.customer.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

@Configuration
@Getter
@Setter
public class ApplicationProperties {

    @NotNull
    @Value("${customer.life-expectancy}")
    private Integer lifeExpectancy;

    @NotNull
    @Value("${customer.min-value-statistics}")
    private Integer minDataForStatistics;

    @NotNull
    @Value("${customer.date-format}")
    private String dateFormat;

}