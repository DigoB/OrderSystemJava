package br.com.rodrigobraz.OrderSystemJava.controllers;

import br.com.rodrigobraz.OrderSystemJava.entities.Category;
import br.com.rodrigobraz.OrderSystemJava.entities.PurchaseOrder;
import br.com.rodrigobraz.OrderSystemJava.entities.dto.CategoryDTO;
import br.com.rodrigobraz.OrderSystemJava.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder> getOrderById(@PathVariable Integer id) {

        PurchaseOrder order = service.getOrderById(id);

        return ResponseEntity.ok().body(order);
    }

    @PostMapping
    public ResponseEntity<Void> insertPurchaseOrder(@Valid @RequestBody PurchaseOrder purchaseOrder) {
        purchaseOrder = service.insertOrder(purchaseOrder);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(purchaseOrder.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
