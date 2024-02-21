package br.com.rodrigobraz.OrderSystemJava.controllers;

import br.com.rodrigobraz.OrderSystemJava.entities.dto.CustomerDTO;
import br.com.rodrigobraz.OrderSystemJava.entities.dto.CustomerInsertDTO;
import br.com.rodrigobraz.OrderSystemJava.entities.Customer;
import br.com.rodrigobraz.OrderSystemJava.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    CustomerService service;

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        Customer customer = service.getCustomerById(id);

        return ResponseEntity.ok().body(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCustomer(@Valid @RequestBody CustomerDTO dto, @PathVariable Integer id) {
        Customer customer = service.fromDTO(dto);
        customer.setId(id);
        customer = service.updateCustomer(customer);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Integer id) {

        service.deletecustomerById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<Customer> customers = service.findAll();
        List<CustomerDTO> listDTO = customers.stream().map(CustomerDTO::new).toList();
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CustomerDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Customer> list =  service.findPage(page, linesPerPage, orderBy, direction);
        Page<CustomerDTO> listDto = list.map(CustomerDTO::new);

        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<Void> insertCustomer(@Valid @RequestBody CustomerInsertDTO dto) {
        Customer customer = service.fromDTO(dto);
        customer = service.createcustomer(customer);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(customer.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
