package br.com.rodrigobraz.OrderSystemJava.controllers;

import br.com.rodrigobraz.OrderSystemJava.entities.OrderBuy;
import br.com.rodrigobraz.OrderSystemJava.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/{id}")
    public ResponseEntity<OrderBuy> getOrderById(@PathVariable Integer id) {

        OrderBuy order = service.getOrderById(id);

        return ResponseEntity.ok().body(order);

    }
}
