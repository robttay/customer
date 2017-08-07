package com.example.customer.service;


import com.example.customer.model.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Test
    public void testTransactional() {
        Customer customer1 = createTestCustomer();
        Customer customer2 = createTestCustomer();
        List<Customer> customers = new ArrayList<>();
        customers.add(customer1);
        customers.add(customer2);

        customer2.setFirstName(null);
        try {
            customerService.add(customer2);
        } catch(Exception e) {
        }

        customers = customerService.get();
        Customer customer3 = findInList(customers, customer1.getFirstName(), customer1.getLastName());
        Assert.assertNull("The first customer created should have rolled back", customer3);
    }


    private Customer createTestCustomer() {
        String firstName = Long.toString(System.currentTimeMillis());
        String lastName = Long.toString(System.currentTimeMillis());

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        return customer;
    }


    private Customer findInList(List<Customer> customers, String first, String last) {
        for (Customer customer : customers) {
            if (customer.getFirstName().equals(first) && customer.getLastName().equals(last)) {
                return customer;
            }
        }
        return null;
    }




}
