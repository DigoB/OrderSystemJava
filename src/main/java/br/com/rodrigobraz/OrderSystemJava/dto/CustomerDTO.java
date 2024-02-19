package br.com.rodrigobraz.OrderSystemJava.dto;

import br.com.rodrigobraz.OrderSystemJava.entities.Customer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

public class CustomerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty(message = "Mandatory filling")
    @Length(min = 5, max = 80, message = "Lenght must have between 5 and 80 characters")
    private String name;

    @NotEmpty(message = "Mandatory filling")
    @Email(message = "Invalid email")
    private String email;

    public CustomerDTO() {}

    public CustomerDTO(Customer customer) {
        id = customer.getId();
        name = customer.getName();
        email = customer.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
