package br.com.rodrigobraz.OrderSystemJava.entities;

import br.com.rodrigobraz.OrderSystemJava.entities.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class Payment implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    private Integer status;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="order_id")
    @MapsId
    private PurchaseOrder order;

    public Payment() {
    }

    public Payment(Integer id, PaymentStatus status, PurchaseOrder order) {
        super();
        this.id = id;
        this.status = (status == null) ? null :  status.getCode();
        this.order = order;
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

    public PurchaseOrder getOrder() {
        return order;
    }

    public void setOrder(PurchaseOrder order) {
        this.order = order;
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
