package com.example.customer.repository;

import com.example.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository{


    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String INSERT_SQL = "INSERT INTO customer (firstName, lastName) VALUES (?,?)";
    @Override
    public void add( Customer customer) {
        jdbcTemplate.update(INSERT_SQL, customer.getFirstName(), customer.getLastName());
    }

    private final String SELECT_BY_ID_SQL = "select * from customer where id = ?";
    @Override
    public Customer getById(int id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, new CustomerMapper(), id);
    }

    private final String SELECT_SQL = "select * from customer";
    @Override
    public List<Customer> get() {
        return jdbcTemplate.query(SELECT_SQL, new CustomerMapper());
    }

    private final String UPDATE_SQL = "update customer set firstName=?, lastName=? where id=?";
    @Override
    public void update(Customer customer) {
        jdbcTemplate.update(UPDATE_SQL, customer.getFirstName(), customer.getLastName(), customer.getId());
    }

    private final String DELETE_SQL = "delete from customer where id=?";
    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }


    @Override
    public void delete(List<Integer> ids) {
        ids.stream().forEach( id -> delete(id) );
    }


    // Map a row of the result set to a person object
    private static class CustomerMapper implements RowMapper<Customer> {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer customer = new Customer();
            customer.setId(rs.getInt("id"));
            customer.setFirstName(rs.getString("firstName"));
            customer.setLastName(rs.getString("lastName"));
            return customer;
        }

    }

}
