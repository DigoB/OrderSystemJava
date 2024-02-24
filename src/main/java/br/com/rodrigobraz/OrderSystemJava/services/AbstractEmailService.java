package br.com.rodrigobraz.OrderSystemJava.services;

import br.com.rodrigobraz.OrderSystemJava.entities.PurchaseOrder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(PurchaseOrder purchaseOrder) {
        SimpleMailMessage simpleMailMessage = prepareSimpleMailMessageFromPurchaseOrder(purchaseOrder);

        sendEmail(simpleMailMessage);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPurchaseOrder(PurchaseOrder purchaseOrder) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(purchaseOrder.getCustomer().getEmail());
        simpleMailMessage.setFrom(sender);
        simpleMailMessage.setSubject("Order confirmed! Code: " + purchaseOrder.getItems());
        simpleMailMessage.setSentDate(new Date(System.currentTimeMillis()));
        simpleMailMessage.setText(purchaseOrder.toString());

        return simpleMailMessage;
    }


}
