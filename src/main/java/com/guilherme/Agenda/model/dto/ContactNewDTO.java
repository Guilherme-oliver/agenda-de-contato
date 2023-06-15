package com.guilherme.Agenda.model.dto;

import com.guilherme.Agenda.services.validation.ContactInsert;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ContactInsert
public class ContactNewDTO {

    private Long id;

    @NotEmpty
    @Size(min = 2, max = 20, message = "Length must be between 2 and 80 characters!")
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 20, message = "Length must be between 2 and 80 characters!")
    private String lastName;
    private Long cep;

    @NotEmpty
    @Size(min = 2, max = 20, message = "Length must be between 2 and 80 characters!")
    private String publicPlaceOrRoad;

    @NotEmpty
    private Integer number;

    @NotEmpty
    @Size(min = 2, max = 20, message = "Length must be between 2 and 80 characters!")
    private String city;

    @NotEmpty
    @Size(min = 2, max = 20, message = "Length must be between 2 and 80 characters!")
    private String state;

    @NotEmpty
    private Integer numberPhone;

    @NotEmpty
    private Integer ddd;

    private Long idAddress;
    private Long idPhone;

    public ContactNewDTO(){}

    public Long getId(){ return id; }

    public void setId(Long id) { this.id = id; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getCep() {
        return cep;
    }

    public void setCep(Long cep) {
        this.cep = cep;
    }

    public String getPublicPlaceOrRoad() {
        return publicPlaceOrRoad;
    }

    public void setPublicPlaceOrRoad(String publicPlaceOrRoad) {
        this.publicPlaceOrRoad = publicPlaceOrRoad;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(Integer numberPhone) {
        this.numberPhone = numberPhone;
    }

    public Integer getDdd() {
        return ddd;
    }

    public void setDdd(Integer ddd) {
        this.ddd = ddd;
    }

    public Long getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Long idAddress) {
        this.idAddress = idAddress;
    }

    public Long getIdPhone() {
        return idPhone;
    }

    public void setIdPhone(Long idPhone) {
        this.idPhone = idPhone;
    }
}