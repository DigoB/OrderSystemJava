package br.com.rodrigobraz.OrderSystemJava.entities;

import br.com.rodrigobraz.OrderSystemJava.entities.enums.PaymentStatus;
import jakarta.persistence.Entity;

@Entity
public class CardPayment extends Payment {

    private Integer quantOfInstallments;

    public CardPayment() {}

    public CardPayment(Integer id, PaymentStatus status, OrderBuy orderBuy, Integer quantOfInstallments) {
        super(id, status, orderBuy);
        this.quantOfInstallments = quantOfInstallments;
    }

    public Integer getQuantOfInstallments() {
        return quantOfInstallments;
    }

    public void setQuantOfInstallments(Integer quantOfInstallments) {
        this.quantOfInstallments = quantOfInstallments;
    }


}
