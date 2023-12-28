package br.com.rodrigobraz.OrderSystemJava.entities;

import br.com.rodrigobraz.OrderSystemJava.entities.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private Integer status;

    // Faz com que o Id do pagamento seja o mesmo Id do pedido
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "ORDER_ID")
    @MapsId
    private OrderBuy orderBuy;

    public Payment() {
    }

    public Payment(Integer id, PaymentStatus status, OrderBuy orderBuy) {
        this.id = id;
        this.status = status.getCode();
        this.orderBuy = orderBuy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentStatus getStatus() {
        return PaymentStatus.toEnum(status);
    }

    public void setStatus(PaymentStatus status) {
        this.status = status.getCode();
    }

    public OrderBuy getOrder() {
        return orderBuy;
    }

    public void setOrder(OrderBuy orderBuy) {
        this.orderBuy = orderBuy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
