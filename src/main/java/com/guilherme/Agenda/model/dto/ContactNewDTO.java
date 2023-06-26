package com.guilherme.Agenda.model.dto;

import com.guilherme.Agenda.services.validation.ContactInsert;

@ContactInsert
public class ContactNewDTO {

   private String firstName;
   private String lastName;
   private PhoneDTO phoneDTO;
   private AddressDTO addressDTO;

   public ContactNewDTO() {
   }

    public ContactNewDTO(String firstName, String lastName, PhoneDTO phoneDTO, AddressDTO addressDTO) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneDTO = phoneDTO;
        this.addressDTO = addressDTO;
    }

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

    public PhoneDTO getPhoneDTO() {
        return phoneDTO;
    }

    public void setPhoneDTO(PhoneDTO phoneDTO) {
        this.phoneDTO = phoneDTO;
    }

    public AddressDTO getAddressDTO() {
        return addressDTO;
    }

    public void setAddressDTO(AddressDTO addressDTO) {
        this.addressDTO = addressDTO;
    }
}