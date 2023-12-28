package br.com.rodrigobraz.OrderSystemJava.entities;

import br.com.rodrigobraz.OrderSystemJava.entities.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class TicketPayment extends Payment {

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dueDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date paymentDate;

    public TicketPayment() {}

    public TicketPayment(Integer id, PaymentStatus status, OrderBuy orderBuy, Date dueDate, Date paymentDate) {
        super(id, status, orderBuy);
        this.dueDate = dueDate;
        this.paymentDate = paymentDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
