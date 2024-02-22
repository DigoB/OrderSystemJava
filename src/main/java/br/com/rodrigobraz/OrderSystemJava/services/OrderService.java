package br.com.rodrigobraz.OrderSystemJava.services;

import br.com.rodrigobraz.OrderSystemJava.entities.OrderItem;
import br.com.rodrigobraz.OrderSystemJava.entities.PurchaseOrder;
import br.com.rodrigobraz.OrderSystemJava.entities.TicketPayment;
import br.com.rodrigobraz.OrderSystemJava.entities.enums.PaymentStatus;
import br.com.rodrigobraz.OrderSystemJava.exceptions.ObjectNotFoundException;
import br.com.rodrigobraz.OrderSystemJava.repositories.OrderBuyRepository;
import br.com.rodrigobraz.OrderSystemJava.repositories.OrderItemRepository;
import br.com.rodrigobraz.OrderSystemJava.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderBuyRepository orderRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private TicketService ticketService;
    @Autowired
    private ProductService productService;

    public PurchaseOrder getOrderById(Integer id) {
        Optional<PurchaseOrder> order = orderRepository.findById(id);
        return order.orElseThrow(() -> new ObjectNotFoundException(
                "Order not found! Id: " + id + ", Type: " + PurchaseOrder.class.getName()));
    }

    @Transactional
    public PurchaseOrder insertOrder(PurchaseOrder purchaseOrder) {
        purchaseOrder.setId(null);
        purchaseOrder.setTimestamp(new Date());
        purchaseOrder.getPayment().setStatus(PaymentStatus.PENDING);
        purchaseOrder.getPayment().setOrder(purchaseOrder);

        if (purchaseOrder.getPayment() instanceof TicketPayment) {
            TicketPayment payment = (TicketPayment) purchaseOrder.getPayment();
            ticketService.fillTicketPayment(payment, purchaseOrder.getTimestamp());
        }

        purchaseOrder = orderRepository.save(purchaseOrder);
        paymentRepository.save(purchaseOrder.getPayment());

        for (OrderItem item : purchaseOrder.getItems()) {
            item.setDiscount(0.0);
            item.setPrice(productService.getProductById(item.getProduct().getId()).getPrice());
            item.setOrder(purchaseOrder);
        }

        orderItemRepository.saveAll(purchaseOrder.getItems());

        return purchaseOrder;
    }
}
