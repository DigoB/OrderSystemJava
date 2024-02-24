package br.com.rodrigobraz.OrderSystemJava.services;

import br.com.rodrigobraz.OrderSystemJava.entities.PurchaseOrder;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(PurchaseOrder purchaseOrder);

    void sendEmail(SimpleMailMessage message);
}
