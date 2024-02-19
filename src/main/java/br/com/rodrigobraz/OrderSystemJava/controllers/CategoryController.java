package br.com.rodrigobraz.OrderSystemJava.controllers;

import br.com.rodrigobraz.OrderSystemJava.entities.Category;
import br.com.rodrigobraz.OrderSystemJava.repositories.CategoryRepository;
import br.com.rodrigobraz.OrderSystemJava.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Integer id) {

        Category category = categoryService.getCategoryById(id);

        return ResponseEntity.ok().body(category);
    }

    @PostMapping
    public ResponseEntity<Void> insertCategory(@RequestBody Category category) {
        category = categoryService.createCategory(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@RequestBody Category category, @PathVariable Integer id) {
        category.setId(id);
        category = categoryService.updateCategory(category);
        return ResponseEntity.noContent().build();
    }

}
