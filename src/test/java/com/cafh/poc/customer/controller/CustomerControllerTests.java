package com.cafh.poc.customer.controller;


import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cafh.poc.customer.business.Customer;
import com.cafh.poc.customer.business.CustomerKpi;
import com.cafh.poc.customer.business.CustomerOperations;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTests {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerOperations customerOperations;

    @Mock
    private CustomerRestAdapter customerRestAdapter;

    @Test
    public void testCreateCustomer() {
        CustomerRequest request = CustomerRequest.builder()
                .age(34).dateOfBirth("23/12/1984").name("Carlos").lastName("Fernandez").build();
        Customer customer = Customer.builder()
                .age(34).dateOfBirth(1554737410373L)
                .name("Carlos").lastName("Fernandez").build();
        when(customerRestAdapter.convertRequestToDomain(eq(request))).thenReturn(customer);
        doNothing().when(customerOperations).createCustomer(eq(customer));
        customerController.createCustomer(request);
        verify(customerOperations, times(1)).createCustomer(eq(customer));
        verify(customerRestAdapter, times(1)).convertRequestToDomain(eq(request));
    }

    @Test
    public void testGetCustomerKpi(){
        CustomerKpi customerKpi = CustomerKpi.builder()
                .ageAverage(34.00)
                .ageStandardDeviation(6.6583281184794)
                .build();
        CustomerKpiResponse customerKpiResponse = CustomerKpiResponse.builder()
                .ageAvg(34.00)
                .ageStDev(6.6583281184794)
                .build();
        when(customerOperations.getCustomerKpi()).thenReturn(customerKpi);
        when(customerRestAdapter.convertKpiToResponse(eq(customerKpi))).thenReturn(customerKpiResponse);
        CustomerKpiResponse result = customerController.getCustomerKpi();
        verify(customerOperations, times(1)).getCustomerKpi();
        verify(customerRestAdapter, times(1)).convertKpiToResponse(eq(customerKpi));
        assertEquals(result.getAgeAvg(), customerKpi.getAgeAverage());
        assertEquals(result.getAgeStDev(), customerKpi.getAgeStandardDeviation());
    }

    @Test
    public void testGetCustomerList() {
        Customer customer = Customer.builder()
                .age(34).dateOfBirth(1554737410373L)
                .name("Carlos").lastName("Fernandez").build();
        CustomerResponse response = CustomerResponse.builder()
                .age(34).dateOfBirth(1554737410373L)
                .name("Carlos").lastName("Fernandez").build();
        when(customerOperations.getCustomerList()).thenReturn(Arrays.asList(customer));
//        when(customerRestAdapter.convertDomainListToResponseList(anyList())).thenReturn(Arrays.asList(response));
        List<Customer> result = customerOperations.getCustomerList();
        assertEquals(result.get(0).getName(), customer.getName());
        assertEquals(result.get(0).getLastName(), customer.getLastName());
        assertEquals(result.get(0).getAge(), customer.getAge());
        assertEquals(result.get(0).getDateOfBirth(), customer.getDateOfBirth());
    }

}
