package br.com.rodrigobraz.OrderSystemJava.services;

import br.com.rodrigobraz.OrderSystemJava.entities.Customer;
import br.com.rodrigobraz.OrderSystemJava.exceptions.ObjectNotFoundException;
import br.com.rodrigobraz.OrderSystemJava.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer getCustomerById(Integer id) {
        Optional<Customer> customer =  repository.findById(id);

        return customer.orElseThrow(() ->
                new ObjectNotFoundException("Customer not found! Id: " + id + ", Type: " +
                        Customer.class.getName()));
    }
}
