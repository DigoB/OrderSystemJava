package br.com.rodrigobraz.OrderSystemJava.services.validators;

import br.com.rodrigobraz.OrderSystemJava.entities.Customer;
import br.com.rodrigobraz.OrderSystemJava.entities.dto.CustomerInsertDTO;
import br.com.rodrigobraz.OrderSystemJava.entities.enums.CustomerType;
import br.com.rodrigobraz.OrderSystemJava.exceptions.FieldMessage;
import br.com.rodrigobraz.OrderSystemJava.repositories.CustomerRepository;
import br.com.rodrigobraz.OrderSystemJava.services.validators.utils.BR;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerInsertValidator implements ConstraintValidator<CpfCnpj, CustomerInsertDTO> {

    @Autowired
    private CustomerRepository repository;

    @Override
    public void initialize(CpfCnpj ann) {
    }

    @Override
    public boolean isValid(CustomerInsertDTO objDto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        if (objDto.getType().equals(CustomerType.NATURAL_PERSON.getCode()) && !BR.isValidCPF(objDto.getDocument())) {
            list.add(new FieldMessage("document", "Invalid CPF."));
        }

        if (objDto.getType().equals(CustomerType.LEGAL_ENTITY.getCode()) && !BR.isValidCNPJ(objDto.getDocument())) {
            list.add(new FieldMessage("document", "Invalid CNPJ."));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}