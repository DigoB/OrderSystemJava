package br.com.rodrigobraz.OrderSystemJava.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderItemPK implements Serializable {

    private static final long serialversionUID = 1L;


    // Classe auxiliar para identificar os itens do pedido

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private OrderBuy orderBuy;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    public OrderBuy getOrderBuy() {
        return orderBuy;
    }

    public void setOrderBuy(OrderBuy orderBuy) {
        this.orderBuy = orderBuy;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemPK that = (OrderItemPK) o;
        return Objects.equals(orderBuy, that.orderBuy) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderBuy, product);
    }
}
