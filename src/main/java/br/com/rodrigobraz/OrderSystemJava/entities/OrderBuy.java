package br.com.rodrigobraz.OrderSystemJava.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class OrderBuy implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date timestamp;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "orderBuy")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "DELIVERY_ADDRESS_ID")
    private Address deliveryAddress;

    @OneToMany(mappedBy = "id.orderBuy")
    private Set<OrderItem> orderItems = new HashSet<>();

    public OrderBuy() {
    }

    public OrderBuy(Integer id, Date timestamp, Customer customer, Address deliveryAddress) {
        this.id = id;
        this.timestamp = timestamp;
        this.customer = customer;
        this.deliveryAddress = deliveryAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderBuy orderBuy = (OrderBuy) o;
        return Objects.equals(id, orderBuy.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
