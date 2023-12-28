package br.com.rodrigobraz.OrderSystemJava.services;

import br.com.rodrigobraz.OrderSystemJava.entities.Category;
import br.com.rodrigobraz.OrderSystemJava.entities.OrderBuy;
import br.com.rodrigobraz.OrderSystemJava.exceptions.ObjectNotFoundException;
import br.com.rodrigobraz.OrderSystemJava.repositories.CategoryRepository;
import br.com.rodrigobraz.OrderSystemJava.repositories.OrderBuyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderBuyRepository repository;

    public OrderBuy getOrderById(Integer id) {
        Optional<OrderBuy> order = repository.findById(id);
        return order.orElseThrow(() -> new ObjectNotFoundException(
                "Order not found! Id: " + id + ", Type: " + OrderBuy.class.getName()));
    }

}
