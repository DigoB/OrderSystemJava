package br.com.rodrigobraz.OrderSystemJava.repositories;

import br.com.rodrigobraz.OrderSystemJava.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
