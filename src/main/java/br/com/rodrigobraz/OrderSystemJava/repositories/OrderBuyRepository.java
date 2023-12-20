package br.com.rodrigobraz.OrderSystemJava.repositories;

import br.com.rodrigobraz.OrderSystemJava.entities.OrderBuy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderBuyRepository extends JpaRepository<OrderBuy, Integer> {
}
