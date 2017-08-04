package com.example.customer.service;

import com.example.customer.model.Customer;
import com.example.customer.repository.CustomerRepository;

import java.util.List;

public interface CustomerService extends CustomerRepository{
    @Override
    void add(Customer customer);

    @Override
    Customer getById(int id);

    @Override
    List<Customer> get();

    @Override
    void update(Customer customer);

    @Override
    void delete(int id);
}
