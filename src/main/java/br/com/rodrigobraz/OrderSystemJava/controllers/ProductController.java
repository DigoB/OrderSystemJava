package br.com.rodrigobraz.OrderSystemJava.controllers;

import br.com.rodrigobraz.OrderSystemJava.controllers.utils.URL;
import br.com.rodrigobraz.OrderSystemJava.entities.Category;
import br.com.rodrigobraz.OrderSystemJava.entities.Product;
import br.com.rodrigobraz.OrderSystemJava.entities.dto.CategoryDTO;
import br.com.rodrigobraz.OrderSystemJava.entities.dto.ProductDTO;
import br.com.rodrigobraz.OrderSystemJava.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getOrderById(@PathVariable Integer id) {

        Product product = service.getProductById(id);

        return ResponseEntity.ok().body(product);
    }

    @GetMapping()
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "categories", defaultValue = "") String categories,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        List<Integer> ids = URL.decodeIntList(categories);
        String decodedName = URL.decodeParam(name);

        Page<Product> list =  service.search(decodedName, ids,page, linesPerPage, orderBy, direction);
        Page<ProductDTO> listDto = list.map(ProductDTO::new);

        return ResponseEntity.ok().body(listDto);
    }
}
