package br.com.rodrigobraz.OrderSystemJava.services;

import br.com.rodrigobraz.OrderSystemJava.entities.PurchaseOrder;
import br.com.rodrigobraz.OrderSystemJava.exceptions.ObjectNotFoundException;
import br.com.rodrigobraz.OrderSystemJava.repositories.OrderBuyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderBuyRepository repository;

    public PurchaseOrder getOrderById(Integer id) {
        Optional<PurchaseOrder> order = repository.findById(id);
        return order.orElseThrow(() -> new ObjectNotFoundException(
                "Order not found! Id: " + id + ", Type: " + PurchaseOrder.class.getName()));
    }

}
