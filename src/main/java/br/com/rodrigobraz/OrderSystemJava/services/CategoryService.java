package br.com.rodrigobraz.OrderSystemJava.services;

import br.com.rodrigobraz.OrderSystemJava.entities.Category;
import br.com.rodrigobraz.OrderSystemJava.exceptions.DataIntegrityException;
import br.com.rodrigobraz.OrderSystemJava.exceptions.ObjectNotFoundException;
import br.com.rodrigobraz.OrderSystemJava.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

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
        //getCategoryById(category.getId());
        return repository.save(category);
    }

    public void deleteCategoryById(Integer id) {
        getCategoryById(id);
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Can not delete a category that has products");
        }
    }
}
