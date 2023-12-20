package br.com.rodrigobraz.OrderSystemJava.repositories;

import br.com.rodrigobraz.OrderSystemJava.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
