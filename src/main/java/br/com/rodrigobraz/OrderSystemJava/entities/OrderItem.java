package br.com.rodrigobraz.OrderSystemJava.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

@Entity
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Double discount;
    private Integer quantity;
    private Double price;

    public OrderItem() {
    }

    public OrderItem(PurchaseOrder order, Product product, Double discount, Integer quantity, Double price) {
        super();
        id.setOrder(order);
        id.setProduct(product);
        this.discount = discount;
        this.quantity = quantity;
        this.price = price;
    }

    public double getSubtotal() {
        return (price - discount) * quantity;
    }

    @JsonIgnore
    public PurchaseOrder getOrder() {
        return id.getOrder();
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setOrder(PurchaseOrder purchaseOrder) {
        id.setOrder(purchaseOrder);
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public OrderItemPK getId() {
        return id;
    }

    public void setId(OrderItemPK id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        StringBuilder builder = new StringBuilder();
        builder.append(getProduct().getName());
        builder.append(", quantity = ");
        builder.append(getQuantity());
        builder.append(", unit price = ");
        builder.append(numberFormat.format(getPrice()));
        builder.append(", subtotal = ");
        builder.append(numberFormat.format(getSubtotal()));
        builder.append("\n");

        return builder.toString();
    }


}
