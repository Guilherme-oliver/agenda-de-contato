package com.guilherme.Agenda.services;

import com.guilherme.Agenda.model.Address;
import com.guilherme.Agenda.model.Contact;
import com.guilherme.Agenda.model.Phone;
import com.guilherme.Agenda.model.dto.ContactDTO;
import com.guilherme.Agenda.model.dto.ContactNewDTO;
import com.guilherme.Agenda.repositories.AddressRepository;
import com.guilherme.Agenda.repositories.ContactRepository;
import com.guilherme.Agenda.repositories.PhoneRepository;
import com.guilherme.Agenda.services.exceptions.DatabaseException;
import com.guilherme.Agenda.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private AddressRepository addressRepository;

    public Contact findById(Long id){
        Optional<Contact> object = repository.findById(id);
        return object.orElse(null);
    }

    public List<Contact> findAll(){
        return repository.findAll();
    }

    public Optional<Contact> findByFirstName(String firstName){
        return Optional.ofNullable(repository.findByFirstName(firstName).orElse(null));
    }

    public Optional<Contact> findByLastName(String lastName){
        return Optional.ofNullable(repository.findByFirstName(lastName).orElse(null));
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

    public Contact fromNewDTO(ContactNewDTO contactNewDTO){
        Contact contact = new Contact(null, contactNewDTO.getFirstName(), contactNewDTO.getLastName());
        Address address = new Address(contactNewDTO.getIdAddress(), contactNewDTO.getCep(), contactNewDTO.getPublicPlaceOrRoad(), contactNewDTO.getNumber(), contactNewDTO.getCity(), contactNewDTO.getState(), contact);
        Phone phone = new Phone(contactNewDTO.getIdPhone(), contactNewDTO.getNumberPhone(), contactNewDTO.getDdd(), contact);
        contact.getAddresses().add(address);
        contact.getPhones().add(phone);
        return contact;
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

    public Contact insert(Contact contact){
        contact.setId(null);
        addressRepository.saveAll(contact.getAddresses());
        phoneRepository.saveAll(contact.getPhones());
        return repository.save(contact);
    }

    public Page<Contact> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

}