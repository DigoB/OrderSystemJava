package br.com.rodrigobraz.OrderSystemJava.controllers;

import br.com.rodrigobraz.OrderSystemJava.entities.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    @GetMapping
    public List<Category> list() {

        Category cat1 = new Category(1, "Informática");
        Category cat2 = new Category(2, "Escritório");

        List<Category> categories = new ArrayList<>();
        categories.add(cat1);
        categories.add(cat2);

        return categories;
    }

}
