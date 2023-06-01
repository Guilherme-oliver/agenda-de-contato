package com.guilherme.Agenda.model.dto;

import com.guilherme.Agenda.model.Contact;
import com.guilherme.Agenda.services.validation.ContactUpdate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@ContactUpdate
public class ContactDTO {

    private Long id;

    @NotBlank(message = "mandatory field!")
    @Size(min = 2, max = 80, message = "Minimum 2 and maximum 80 characters")
    private String firstName, lastName;

    public ContactDTO convert(Contact contact) {
        this.firstName = contact.getFirstName();
        this.lastName = contact.getLastName();
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}