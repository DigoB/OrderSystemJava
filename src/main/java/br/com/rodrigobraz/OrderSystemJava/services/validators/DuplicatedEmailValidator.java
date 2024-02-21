package br.com.rodrigobraz.OrderSystemJava.services.validators;

import br.com.rodrigobraz.OrderSystemJava.entities.dto.CustomerInsertDTO;
import br.com.rodrigobraz.OrderSystemJava.entities.Customer;
import br.com.rodrigobraz.OrderSystemJava.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class DuplicatedEmailValidator implements Validator {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerInsertDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasErrors()) {
            return;
        }

        CustomerInsertDTO dto = (CustomerInsertDTO) target;

        Optional<Customer> possibleCustomer = customerRepository.findByEmail(dto.getEmail());

        if (possibleCustomer.isPresent()) {
            errors.rejectValue("email", null, "Email already registered!");
        }
    }

    @Override
    public Errors validateObject(Object target) {
        return Validator.super.validateObject(target);
    }

}
