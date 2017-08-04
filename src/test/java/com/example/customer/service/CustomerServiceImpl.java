package com.example.customer.service;

import com.example.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    @Autowired
    @Transactional
    @Override
    public void add(Customer customer) {

    }
    @Transactional
    @Override
    public Customer getById(int id) {
        return null;
    }
    @Transactional
    @Override
    public List<Customer> get() {
        return null;
    }
    @Transactional
    @Override
    public void update(Customer customer) {

    }
    @Transactional
    @Override
    public void delete(int id) {

    }
}
