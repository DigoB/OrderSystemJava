package br.com.rodrigobraz.OrderSystemJava.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;


@Service
public class MockEmailService extends AbstractEmailService {

    private static final Logger logger = LoggerFactory.getLogger(MockEmailService.class);
    @Override
    public void sendEmail(SimpleMailMessage message) {
        logger.info("Simulation email send...");
        logger.info(message.toString());
        logger.info("Email sent...");
    }
}
