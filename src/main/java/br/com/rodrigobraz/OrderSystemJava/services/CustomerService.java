package br.com.rodrigobraz.OrderSystemJava.services;

import br.com.rodrigobraz.OrderSystemJava.dto.CustomerDTO;
import br.com.rodrigobraz.OrderSystemJava.entities.Customer;
import br.com.rodrigobraz.OrderSystemJava.entities.Customer;
import br.com.rodrigobraz.OrderSystemJava.exceptions.DataIntegrityException;
import br.com.rodrigobraz.OrderSystemJava.exceptions.ObjectNotFoundException;
import br.com.rodrigobraz.OrderSystemJava.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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

    /** public Customer createcustomer(Customer customer) {
        customer.setId(null);
        return repository.save(customer);

    } */

    public Customer updateCustomer(Customer customer) {
        //getcustomerById(customer.getId());
        Customer newCustomer = getCustomerById(customer.getId());
        updateData(newCustomer, customer);
        return repository.save(newCustomer);
    }

    public void deletecustomerById(Integer id) {
        getCustomerById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Can not delete because there is related entities");
        }
    }

    public List<Customer> findAll() {
        return repository.findAll();
    }

    public Page<Customer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Customer fromDTO(CustomerDTO customerDTO) {
        return new Customer(customerDTO.getId(), customerDTO.getName(), customerDTO.getEmail(), null, null);
    }

    private void updateData(Customer newCustomer, Customer customer) {
        newCustomer.setName(customer.getName());
        newCustomer.setEmail(customer.getEmail());
    }


}
