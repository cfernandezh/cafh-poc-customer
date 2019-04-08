package com.cafh.poc.customer.business;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.cafh.poc.customer.business.impl.CustomerServices;
import com.cafh.poc.customer.config.ApplicationProperties;
import com.cafh.poc.customer.repository.CustomerEntity;
import com.cafh.poc.customer.repository.CustomerRepository;
import com.cafh.poc.customer.repository.CustomerRepositoryAdapter;
import com.cafh.poc.customer.util.StatisticsUtil;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServicesTests {

    @InjectMocks
    private CustomerServices customerServices;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerRepositoryAdapter customerRepositoryAdapter;

    @Mock
    private StatisticsUtil statisticsUtil;

    @Mock
    private ApplicationProperties applicationProperties;

    @Before
    public void setUp() {
        when(applicationProperties.getLifeExpectancy()).thenReturn(75);
        when(applicationProperties.getMinDataForStatistics()).thenReturn(2);
    }

    @Test
    public void testCreateCustomer() {
        Customer customer = Customer.builder()
                .age(34).dateOfBirth(1554737410373L)
                .name("Carlos").lastName("Fernandez").build();
        CustomerEntity entity = CustomerEntity.builder()
                .id(UUID.randomUUID().toString())
                .age(34).dateOfBirth(1554737410373L)
                .name("Carlos").lastName("Fernandez").build();
        when(customerRepositoryAdapter.convertDomainToEntity(eq(customer))).thenReturn(entity);
        when(customerRepository.save(eq(entity))).thenReturn(entity);
        customerServices.createCustomer(customer);
        verify(customerRepositoryAdapter, times(1)).convertDomainToEntity(eq(customer));
        verify(customerRepository, times(1)).save(eq(entity));
    }

    @Test
    public void testGetCustomerKpi() {
        Customer customerOne = Customer.builder()
                .age(34).dateOfBirth(1554737410373L)
                .name("Carlos").lastName("Fernandez").build();
        Customer customerTwo = Customer.builder()
                .age(21).dateOfBirth(1554737410373L)
                .name("Juan").lastName("Perez").build();
        Customer customerThree = Customer.builder()
                .age(25).dateOfBirth(1554737410373L)
                .name("Luis").lastName("Gonzales").build();
        CustomerEntity entity = CustomerEntity.builder()
                .id(UUID.randomUUID().toString())
                .age(34).dateOfBirth(1554737410373L)
                .name("Carlos").lastName("Fernandez").build();
        Double ageAverage = 34.00;
        Double ageStandardDeviation = 6.6583281184794;
        when(customerRepositoryAdapter.convertEntityListToDomainList(anyList()))
                .thenReturn(Arrays.asList(customerOne, customerTwo, customerThree));
        when(customerRepository.findAll()).thenReturn(Arrays.asList(entity, entity, entity));
        when(statisticsUtil.getAverage(anyList())).thenReturn(ageAverage);
        when(statisticsUtil.getStDev(anyList())).thenReturn(ageStandardDeviation);
        CustomerKpi result = customerServices.getCustomerKpi();
        verify(customerRepositoryAdapter, times(1)).convertEntityListToDomainList(anyList());
        verify(statisticsUtil, times(1)).getAverage(anyList());
        verify(statisticsUtil, times(1)).getStDev(anyList());
        assertEquals(result.getAgeAverage(), ageAverage);
        assertEquals(result.getAgeStandardDeviation(), ageStandardDeviation);
    }

    @Test
    public void testGetCustomerKpiForOneSample() {
        Customer customer = Customer.builder()
                .age(34).dateOfBirth(1554737410373L)
                .name("Carlos").lastName("Fernandez").build();
        CustomerEntity entity = CustomerEntity.builder()
                .id(UUID.randomUUID().toString())
                .age(34).dateOfBirth(1554737410373L)
                .name("Carlos").lastName("Fernandez").build();
        Double ageAverage = 34.00;
        when(customerRepositoryAdapter.convertEntityListToDomainList(anyList()))
                .thenReturn(Arrays.asList(customer));
        when(customerRepository.findAll()).thenReturn(Arrays.asList(entity, entity, entity));
        when(statisticsUtil.getAverage(anyList())).thenReturn(ageAverage);
        CustomerKpi result = customerServices.getCustomerKpi();
        verify(customerRepositoryAdapter, times(1)).convertEntityListToDomainList(anyList());
        verify(statisticsUtil, times(1)).getAverage(anyList());
        verify(statisticsUtil, times(0)).getStDev(anyList());
        assertEquals(result.getAgeAverage(), ageAverage);
        assertNull(result.getAgeStandardDeviation());
    }


    @Test
    public void testGetCustomerList() {
        Customer customer = Customer.builder()
                .age(34).dateOfBirth(1554737410373L)
                .name("Carlos").lastName("Fernandez").build();
        CustomerEntity entity = CustomerEntity.builder()
                .id(UUID.randomUUID().toString())
                .age(34).dateOfBirth(1554737410373L)
                .name("Carlos").lastName("Fernandez").build();
        when(customerRepositoryAdapter.convertEntityListToDomainList(anyList()))
                .thenReturn(Arrays.asList(customer));
        when(customerRepository.findAll()).thenReturn(Arrays.asList(entity, entity, entity));
        List<Customer> result = customerServices.getCustomerList();
        verify(customerRepositoryAdapter, times(1)).convertEntityListToDomainList(anyList());
        assertEquals(result.get(0).getAge(), customer.getAge());
        assertEquals(result.get(0).getDateOfBirth(), customer.getDateOfBirth());
        assertEquals(result.get(0).getName(), customer.getName());
        assertEquals(result.get(0).getLastName(), customer.getLastName());
    }

}
