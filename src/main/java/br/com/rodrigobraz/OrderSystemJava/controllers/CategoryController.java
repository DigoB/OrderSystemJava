package br.com.rodrigobraz.OrderSystemJava.controllers;

import br.com.rodrigobraz.OrderSystemJava.dto.CategoryDTO;
import br.com.rodrigobraz.OrderSystemJava.entities.Category;
import br.com.rodrigobraz.OrderSystemJava.exceptions.DataIntegrityException;
import br.com.rodrigobraz.OrderSystemJava.repositories.CategoryRepository;
import br.com.rodrigobraz.OrderSystemJava.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<Void> insertCategory(@Valid @RequestBody CategoryDTO dto) {
        Category category = categoryService.fromDTO(dto);
        category = categoryService.createCategory(category);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(category.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@Valid @RequestBody CategoryDTO dto, @PathVariable Integer id) {
        Category category = categoryService.fromDTO(dto);
        category.setId(id);
        category = categoryService.updateCategory(category);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {

        categoryService.deleteCategoryById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        List<CategoryDTO> listDTO = categories.stream().map(CategoryDTO::new).toList();
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CategoryDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        Page<Category> list =  categoryService.findPage(page, linesPerPage, orderBy, direction);
        Page<CategoryDTO> listDto = list.map(CategoryDTO::new);

        return ResponseEntity.ok().body(listDto);
    }

}
