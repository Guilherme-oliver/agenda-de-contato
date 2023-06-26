package com.guilherme.Agenda.model.dto;

public class AddressDTO {

    private Long cep;
    private String publicPlaceOrRoad;
    private Integer number;
    private String city;
    private String state;

    public AddressDTO(){}

    public AddressDTO(Long cep, String publicPlaceOrRoad, Integer number, String city, String state) {
        this.cep = cep;
        this.publicPlaceOrRoad = publicPlaceOrRoad;
        this.number = number;
        this.city = city;
        this.state = state;
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
}
