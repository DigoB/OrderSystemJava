package br.com.rodrigobraz.OrderSystemJava.services;

import br.com.rodrigobraz.OrderSystemJava.entities.Category;
import br.com.rodrigobraz.OrderSystemJava.entities.Product;
import br.com.rodrigobraz.OrderSystemJava.exceptions.ObjectNotFoundException;
import br.com.rodrigobraz.OrderSystemJava.repositories.CategoryRepository;
import br.com.rodrigobraz.OrderSystemJava.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public Product getProductById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        return product.orElseThrow(() -> new ObjectNotFoundException(
                "Product not found! Id: " + id + ", Type: " + Product.class.getName()));
    }

    public Page<Product> search(String name,
                                List<Integer> ids,
                                Integer page,
                                Integer linesPerPage,
                                String orderBy,
                                String direction) {

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        List<Category> categories = categoryRepository.findAllById(ids);

        return productRepository.findDistinctByNameContainingAndCategoriesIn(name, categories, pageRequest);
    }

}