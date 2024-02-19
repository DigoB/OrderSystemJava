package br.com.rodrigobraz.OrderSystemJava.dto;

import br.com.rodrigobraz.OrderSystemJava.entities.Category;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

public class CategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @NotEmpty(message = "Mandatory filling")
    @Length(min = 5, max = 80, message = "Lenght must have between 5 and 80 characters")
    private String name;

    public CategoryDTO() {}

    public CategoryDTO(Category category) {
        id = category.getId();
        name = category.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
