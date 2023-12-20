package br.com.rodrigobraz.OrderSystemJava.repositories;

import br.com.rodrigobraz.OrderSystemJava.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
