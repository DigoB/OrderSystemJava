package br.com.rodrigobraz.OrderSystemJava.entities;

import br.com.rodrigobraz.OrderSystemJava.entities.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.persistence.Entity;

import java.util.Date;

@Entity
@JsonTypeName("ticketPayment")
public class TicketPayment extends Payment {
    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date dueDate;

    @JsonFormat(pattern="dd/MM/yyyy")
    private Date paymentDate;

    public TicketPayment() {
    }

    public TicketPayment(Integer id, PaymentStatus status, PurchaseOrder order, Date dueDate, Date paymentDate) {
        super(id, status, order);
        this.paymentDate = paymentDate;
        this.dueDate = dueDate;
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
