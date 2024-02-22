package br.com.rodrigobraz.OrderSystemJava.services;

import br.com.rodrigobraz.OrderSystemJava.entities.TicketPayment;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TicketService {

    // Simula uma data de vencimento do boleto para 7 dias após o pedido ser criado.
    public void fillTicketPayment(TicketPayment payment, Date orderTimestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(orderTimestamp);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        payment.setPaymentDate(calendar.getTime());
    }
}
