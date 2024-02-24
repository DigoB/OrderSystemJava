package br.com.rodrigobraz.OrderSystemJava.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class PurchaseOrder implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date timestamp;

    @OneToOne(cascade=CascadeType.ALL, mappedBy="order")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="delivery_address_id")
    private Address deliveryAddress;

    @OneToMany(mappedBy="id.order")
    private Set<OrderItem> itens = new HashSet<>();

    public PurchaseOrder() {
    }

    public PurchaseOrder(Integer id, Date timestamp, Customer customer, Address deliveryAddress) {
        super();
        this.id = id;
        this.timestamp = timestamp;
        this.customer = customer;
        this.deliveryAddress = deliveryAddress;
    }

    public double getTotalValue() {
        double sum = 0.0;
        for (OrderItem orderItem : itens) {
            sum += orderItem.getSubtotal();
        }
        return sum;
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

    public Set<OrderItem> getItems() {
        return itens;
    }

    public void setItens(Set<OrderItem> itens) {
        this.itens = itens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseOrder purchaseOrder = (PurchaseOrder) o;
        return Objects.equals(id, purchaseOrder.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

        StringBuilder builder = new StringBuilder();
        builder.append("Order n°: ");
        builder.append(getId());
        builder.append(", timestamp: ");
        builder.append(sdf.format(getTimestamp()));
        builder.append(", customer: ");
        builder.append(getCustomer().getName());
        builder.append(", payment status: ");
        builder.append(getPayment().getStatus().getDescription());
        builder.append("\nDetails:\n");
        for (OrderItem orderItem : getItems()) {
            builder.append(orderItem.toString());
        }
        builder.append("Total value: ");
        builder.append(numberFormat.format(getTotalValue()));

        return builder.toString();
    }
}
