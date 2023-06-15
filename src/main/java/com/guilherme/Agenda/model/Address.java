package com.guilherme.Agenda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long cep;
    private String publicPlaceOrRoad;
    private Integer number;
    private String city;
    private String state;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="contact_id")
    private Contact contact;

    public Address(){}

    public Address(Long id, Long cep, String publicPlaceOrRoad, Integer number, String city, String state, Contact contact) {
        this.id = id;
        this.cep = cep;
        this.publicPlaceOrRoad = publicPlaceOrRoad;
        this.number = number;
        this.city = city;
        this.state = state;
        this.contact = contact;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(getId(), address.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
