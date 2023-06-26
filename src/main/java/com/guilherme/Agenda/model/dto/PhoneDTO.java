package com.guilherme.Agenda.model.dto;

public class PhoneDTO {
    private Integer ddd;
    private Integer numberPhone;

    public PhoneDTO() {
    }

    public PhoneDTO(Integer ddd, Integer numberPhone) {
        this.ddd = ddd;
        this.numberPhone = numberPhone;
    }

    public Integer getDdd() {
        return ddd;
    }

    public void setDdd(Integer ddd) {
        this.ddd = ddd;
    }

    public Integer getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(Integer numberPhone) {
        this.numberPhone = numberPhone;
    }
}
