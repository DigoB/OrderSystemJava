package br.com.rodrigobraz.OrderSystemJava.entities;

import br.com.rodrigobraz.OrderSystemJava.entities.enums.CustomerType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String email;
    private String document;
    private Integer customerType;

    @JsonManagedReference
    @OneToMany(mappedBy = "customer")
    private List<Address> addresses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "PHONE_NUMBER")
    private Set<String> phoneNumbers = new HashSet<>();

    @OneToMany(mappedBy = "customer")
    private List<OrderBuy> orderBuys = new ArrayList<>();

    public Customer() {
    }

    public Customer(Integer id, String name, String email, String document, CustomerType customerType) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.document = document;
        this.customerType = customerType.getCode();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    // Implementacao para tratar o tipo cliente atraves do código
    public CustomerType getCustomerType() {
        return CustomerType.toEnum(customerType);
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType.getCode();
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Set<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Set<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public List<OrderBuy> getOrders() {
        return orderBuys;
    }

    public void setOrders(List<OrderBuy> orderBuys) {
        this.orderBuys = orderBuys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
