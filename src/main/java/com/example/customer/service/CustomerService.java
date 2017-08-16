package com.example.customer.service;

import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;

import java.util.List;

public interface CustomerService {

    Customer add(Customer customer);


    Customer getById(int id);


    List<Customer> getAll();


    void update(Customer customer);

    void delete(int id);
}
