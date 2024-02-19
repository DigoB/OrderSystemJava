package br.com.rodrigobraz.OrderSystemJava.entities;

import br.com.rodrigobraz.OrderSystemJava.entities.enums.PaymentStatus;
import jakarta.persistence.Entity;

@Entity
public class CardPayment extends Payment {
    private static final long serialVersionUID = 1L;

    private Integer quantOfInstallments;

    public CardPayment() {
    }

    public CardPayment(Integer id, PaymentStatus status, PurchaseOrder order, Integer quantOfInstallments) {
        super(id, status, order);
        this.quantOfInstallments = quantOfInstallments;
    }

    public Integer getQuantOfInstallments() {
        return quantOfInstallments;
    }

    public void setQuantOfInstallments(Integer quantOfInstallments) {
        this.quantOfInstallments = quantOfInstallments;
    }
}
