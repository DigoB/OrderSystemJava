package br.com.rodrigobraz.OrderSystemJava.services;

import br.com.rodrigobraz.OrderSystemJava.dto.CategoryDTO;
import br.com.rodrigobraz.OrderSystemJava.entities.Category;
import br.com.rodrigobraz.OrderSystemJava.exceptions.DataIntegrityException;
import br.com.rodrigobraz.OrderSystemJava.exceptions.ObjectNotFoundException;
import br.com.rodrigobraz.OrderSystemJava.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public Category getCategoryById(Integer id) {
        Optional<Category> category = repository.findById(id);
        return category.orElseThrow(() -> new ObjectNotFoundException(
                "Category not found! Id: " + id + ", Type: " + Category.class.getName()));
    }

    public Category createCategory(Category category) {
        category.setId(null);
        return repository.save(category);

    }

    public Category updateCategory(Category category) {
        Category newCategory = getCategoryById(category.getId());
        updateData(newCategory, category);
        return repository.save(newCategory);
    }

    public void deleteCategoryById(Integer id) {
        getCategoryById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Can not delete a category that has products");
        }
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    public Category fromDTO(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getId(), categoryDTO.getName());
    }

    private void updateData(Category newCategory, Category category) {
        newCategory.setName(category.getName());
    }
}
