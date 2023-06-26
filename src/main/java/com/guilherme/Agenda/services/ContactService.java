package com.guilherme.Agenda.services;

import com.guilherme.Agenda.model.Address;
import com.guilherme.Agenda.model.Contact;
import com.guilherme.Agenda.model.Phone;
import com.guilherme.Agenda.model.dto.AddressDTO;
import com.guilherme.Agenda.model.dto.ContactDTO;
import com.guilherme.Agenda.model.dto.ContactNewDTO;
import com.guilherme.Agenda.model.dto.PhoneDTO;
import com.guilherme.Agenda.repositories.AddressRepository;
import com.guilherme.Agenda.repositories.ContactRepository;
import com.guilherme.Agenda.repositories.PhoneRepository;
import com.guilherme.Agenda.services.exceptions.DatabaseException;
import com.guilherme.Agenda.services.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private ContactRepository repository;
    private PhoneRepository phoneRepository;
    private AddressRepository addressRepository;

    public ContactService(ContactRepository repository,
                          PhoneRepository phoneRepository,
                          AddressRepository addressRepository) {
        this.repository = repository;
        this.phoneRepository = phoneRepository;
        this.addressRepository = addressRepository;
    }

    public Contact findById(Long id){
        Optional<Contact> object = repository.findById(id);
        return object.orElse(null);
    }

    public List<Contact> findAll(){
        return repository.findAll();
    }

    public Contact findByFirstName(String firstName){
        return repository.findByFirstName(firstName);
    }

    public Contact findByLastName(String lastName){
        return repository.findByLastName(lastName);
    }

    public void deleteById(Long id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public Contact fromDTO(ContactDTO contactDTO){
        return new Contact(contactDTO.getId(), contactDTO.getFirstName(), contactDTO.getFirstName());
    }

    private void updateData(Contact contactNewDTO, Contact contact){
        contactNewDTO.setFirstName(contact.getFirstName());
        contactNewDTO.setLastName(contact.getLastName());
    }

    public Contact update(Contact contact){
        Contact newContact = findById(contact.getId());
        updateData(newContact, contact);
        return repository.save(newContact);
    }

    public Contact updateContactWithPhoneAndAddress(Long contactId, ContactNewDTO contactNewDTO) {
        Contact contact = repository.findById(contactId)
                .orElseThrow(() -> new IllegalArgumentException("Contact not found."));
        contact.setFirstName(contactNewDTO.getFirstName());
        contact.setLastName(contactNewDTO.getLastName());

        PhoneDTO phoneDTO = contactNewDTO.getPhoneDTO();
        Phone phone = (Phone) contact.getPhone();
        phone.setDdd(phoneDTO.getDdd());
        phone.setNumberPhone(phoneDTO.getNumberPhone());

        AddressDTO addressDTO = contactNewDTO.getAddressDTO();
        Address address = (Address) contact.getAddresses();
        address.setCep(addressDTO.getCep());
        address.setPublicPlaceOrRoad(addressDTO.getPublicPlaceOrRoad());
        address.setNumber(addressDTO.getNumber());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());

        return repository.save(contact);
    }

    public Contact addContact(Contact contact) {
        contact.setId(null);
        Contact existingContactByFirstName = repository.findByFirstName(contact.getFirstName());
        Contact existingContactByLastName = repository.findByLastName(contact.getLastName());

        if (existingContactByFirstName != null) {
            throw new IllegalArgumentException("A contact with the same first name already exists");
        }else {
            if (existingContactByLastName != null) {
                throw new IllegalArgumentException("A contact with the same last name already exists");
            }
        }

        contact = repository.save(contact);
        return contact;
    }

    public Contact addPhoneToContact(Long contactId, PhoneDTO phoneDTO) {
        Contact contact = repository.findById(contactId)
                .orElseThrow(() -> new IllegalArgumentException("Contact not found."));
        Phone phone = new Phone();
        phone.setDdd(phoneDTO.getDdd());
        phone.setNumberPhone(phoneDTO.getNumberPhone());
        contact.getPhones().add(phone);
        return repository.save(contact);
    }

    public Contact addAddressToContact(Long contactId, AddressDTO addressDTO) {
        Contact contact = repository.findById(contactId)
                .orElseThrow(() -> new IllegalArgumentException("Contact not found"));
        Address address = new Address();
        address.setCep(addressDTO.getCep());
        address.setPublicPlaceOrRoad(addressDTO.getPublicPlaceOrRoad());
        address.setNumber(addressDTO.getNumber());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        contact.getAddresses().add(address);
        return repository.save(contact);
    }

    public Page<Contact> showAllContacts(Pageable pageable) {
        return repository.findAll(pageable);
    }
}