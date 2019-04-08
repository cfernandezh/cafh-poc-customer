package com.cafh.poc.customer.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.cafh.poc.customer.business.Customer;
import com.cafh.poc.customer.business.CustomerKpi;
import com.cafh.poc.customer.config.ApplicationProperties;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomerRestAdapterTests {

    @InjectMocks
    private CustomerRestAdapter customerRestAdapter;

    @Mock
    private ApplicationProperties applicationProperties;

    @Before
    public void setUp() {
        when(applicationProperties.getDateFormat()).thenReturn("dd/MM/yyyy");
    }

    @Test
    public void testConvertRequestToDomain() {
        CustomerRequest request = CustomerRequest.builder()
                .age(34).dateOfBirth("23/12/1984").name("Carlos").lastName("Fernandez").build();
        Customer result = customerRestAdapter.convertRequestToDomain(request);
        assertEquals(result.getName(), request.getName());
        assertEquals(result.getLastName(), request.getLastName());
        assertEquals(result.getAge(), request.getAge());
    }

    @Test
    public void testConvertDomainToResponse() {
        Customer customer = Customer.builder()
                .age(34).dateOfBirth(1554737410373L).estimatedDeathDate(1554737410373L)
                .name("Carlos").lastName("Fernandez").build();
        CustomerResponse result = customerRestAdapter.convertDomainToResponse(customer);
        assertEquals(result.getName(), customer.getName());
        assertEquals(result.getLastName(), customer.getLastName());
        assertEquals(result.getAge(), customer.getAge());
    }

    @Test
    public void testConvertDomainListToResponseList() {
        Customer customer = Customer.builder()
                .age(34).dateOfBirth(1554737410373L).estimatedDeathDate(1554737410373L)
                .name("Carlos").lastName("Fernandez").build();
        List<CustomerResponse> result = customerRestAdapter.convertDomainListToResponseList(Arrays.asList(customer));
        assertEquals(result.get(0).getName(), customer.getName());
        assertEquals(result.get(0).getLastName(), customer.getLastName());
        assertEquals(result.get(0).getAge(), customer.getAge());
        assertEquals(result.get(0).getDateOfBirth(), customer.getDateOfBirth());
    }

    @Test
    public void testConvertKpiToResponse() {
        CustomerKpi customerKpi = CustomerKpi.builder()
                .ageAverage(34.00)
                .ageStandardDeviation(6.6583281184794)
                .build();
        CustomerKpiResponse result = customerRestAdapter.convertKpiToResponse(customerKpi);
        assertEquals(result.getAgeAvg(), customerKpi.getAgeAverage());
        assertEquals(result.getAgeStDev(), customerKpi.getAgeStandardDeviation());
    }

}
