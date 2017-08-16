package com.example.customer.service;


import com.example.customer.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Test
    public void testAddGet() {
        Customer customer1 = createTestCustomer();
        customerService.add(customer1);

        List<Customer> customers = customerService.getAll();
        Assert.assertNotNull("should return customers from db", customers);

        Customer foundCustomer1 = findInList(customers, customer1.getFirstname(), customer1.getLastname());

        Assert.assertNotNull("should find added customer in customers returned from db", foundCustomer1);

        int id = foundCustomer1.getId();
        foundCustomer1 = null;
        foundCustomer1 = customerService.getById(id);
        Assert.assertNotNull("should return customer from db", foundCustomer1);
        Assert.assertEquals("first name should match", customer1.getFirstname(),
                foundCustomer1.getFirstname());
        Assert.assertEquals("last name should match", customer1.getLastname(),
                foundCustomer1.getLastname());
        Assert.assertEquals("phone should match", customer1.getPhone(), customer1.getPhone());
        Assert.assertEquals("email name should match", customer1.getEmail(), customer1.getEmail());

    }

    @Test
    public void testUpdate() {
        Customer customer1 = createTestCustomer();
        customerService.add(customer1);

        List<Customer> customers = customerService.getAll();

        Customer customer2 = findInList(customers, customer1.getFirstname(), customer1.getLastname());
        Assert.assertNotNull(customer2);

        String updateFirstName = Long.toString(System.currentTimeMillis());
        String updateLastName = Long.toString(System.currentTimeMillis());

        customer2.setFirstname(updateFirstName);
        customer2.setLastname(updateLastName);
        customerService.update(customer2);

        customers = customerService.getAll();

        Customer customer3 = findInList(customers, updateFirstName, updateLastName);
        Assert.assertNotNull(customer3);
        Assert.assertEquals(customer2.getId(), customer3.getId());
    }

    @Test
    public void testDelete() {
        Customer customer1 = createTestCustomer();
        customerService.add(customer1);

        List<Customer> customers = customerService.getAll();

        Customer customer2 = findInList(customers, customer1.getFirstname(), customer1.getLastname());
        Assert.assertNotNull(customer2);

        customerService.delete(customer2.getId());

        customers = customerService.getAll();
        Customer customer3 = findInList(customers, customer1.getFirstname(), customer1.getLastname());
        Assert.assertNull(customer3);
    }

    private Customer createTestCustomer() {
        String firstName = Long.toString(System.currentTimeMillis());
        String lastName = Long.toString(System.currentTimeMillis());

        Customer customer = new Customer();
        customer.setFirstname(firstName);
        customer.setLastname(lastName);
        return customer;
    }

    private Customer findInList(List<Customer> customers, String first, String last) {
        for (Customer customer : customers) {
            if (customer.getFirstname().equals(first) && customer.getLastname().equals(last)) {
                return customer;
            }
        }
        return null;
    }




}
