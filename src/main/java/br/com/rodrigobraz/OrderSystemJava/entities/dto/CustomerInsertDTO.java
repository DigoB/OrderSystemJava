package br.com.rodrigobraz.OrderSystemJava.entities.dto;

import br.com.rodrigobraz.OrderSystemJava.entities.Customer;
import br.com.rodrigobraz.OrderSystemJava.services.validators.CpfCnpj;
import br.com.rodrigobraz.OrderSystemJava.services.validators.UniqueValue;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@CpfCnpj
public class CustomerInsertDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = "Mandatory filling")
    @Length(min = 3, max = 80, message = "Lenght must have between 5 and 80 characters")
    private String name;

    @Column(unique = true)
    @NotEmpty(message = "Mandatory filling")
    @Email(message = "Invalid email")
    @UniqueValue(domainClass = Customer.class, fieldName = "email")
    private String email;

    @NotEmpty(message = "Mandatory filling")
    private String document;
    private Integer type;

    @NotEmpty(message = "Mandatory filling")
    private String street;

    @NotEmpty(message = "Mandatory filling")
    private String number;
    private String complement;
    private String neighborhoor;

    @NotEmpty(message = "Mandatory filling")
    private String zipCode;

    @NotEmpty(message = "Mandatory filling")
    private String phone1;
    private String phone2;
    private String phone3;
    private Integer cityId;

    public CustomerInsertDTO() {}

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

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhoor() {
        return neighborhoor;
    }

    public void setNeighborhoor(String neighborhoor) {
        this.neighborhoor = neighborhoor;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
}
