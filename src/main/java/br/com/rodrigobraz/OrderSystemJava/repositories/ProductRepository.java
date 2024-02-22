package br.com.rodrigobraz.OrderSystemJava.repositories;

import br.com.rodrigobraz.OrderSystemJava.entities.Category;
import br.com.rodrigobraz.OrderSystemJava.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    //@Query("SELECT DISTINCT p FROM Product p INNER JOIN p.categories cat WHERE p.name LIKE %:name% AND cat IN :categories")
    @Transactional(readOnly = true)
    Page<Product> findDistinctByNameContainingAndCategoriesIn(String name, List<Category> categories, Pageable pageRequest);
}
