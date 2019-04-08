package com.cafh.poc.customer.repository;

import static org.junit.Assert.assertEquals;

import com.cafh.poc.customer.business.Customer;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CustomerRepositoryAdapterTests {

    private CustomerRepositoryAdapter customerRepositoryAdapter;

    @Before
    public void setUp() {
        customerRepositoryAdapter = new CustomerRepositoryAdapter();
    }

    @Test
    public void testConvertDomainToEntity() {
        Customer customer = Customer.builder()
                .age(34).dateOfBirth(1554737410373L)
                .name("Carlos").lastName("Fernandez").build();
        CustomerEntity result = customerRepositoryAdapter.convertDomainToEntity(customer);
        assertEquals(result.getAge(), customer.getAge());
        assertEquals(result.getDateOfBirth(), customer.getDateOfBirth());
        assertEquals(result.getName(), customer.getName());
        assertEquals(result.getLastName(), customer.getLastName());
    }

    @Test
    public void testConvertEntityToDomain() {
        CustomerEntity entity = CustomerEntity.builder()
                .id(UUID.randomUUID().toString())
                .age(34).dateOfBirth(1554737410373L)
                .name("Carlos").lastName("Fernandez").build();
        Customer result = customerRepositoryAdapter.convertEntityToDomain(entity);
        assertEquals(result.getAge(), entity.getAge());
        assertEquals(result.getDateOfBirth(), entity.getDateOfBirth());
        assertEquals(result.getName(), entity.getName());
        assertEquals(result.getLastName(), entity.getLastName());
    }

    @Test
    public void testConvertEntityListToDomainList() {
        CustomerEntity entity = CustomerEntity.builder()
                .id(UUID.randomUUID().toString())
                .age(34).dateOfBirth(1554737410373L)
                .name("Carlos").lastName("Fernandez").build();
        List<Customer> result = customerRepositoryAdapter.convertEntityListToDomainList(Arrays.asList(entity));
        assertEquals(result.get(0).getAge(), entity.getAge());
        assertEquals(result.get(0).getDateOfBirth(), entity.getDateOfBirth());
        assertEquals(result.get(0).getName(), entity.getName());
        assertEquals(result.get(0).getLastName(), entity.getLastName());
    }

}
