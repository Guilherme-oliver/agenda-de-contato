package com.guilherme.Agenda.controller;

import com.guilherme.Agenda.model.Contact;
import com.guilherme.Agenda.model.dto.AddressDTO;
import com.guilherme.Agenda.model.dto.ContactDTO;
import com.guilherme.Agenda.model.dto.ContactNewDTO;
import com.guilherme.Agenda.model.dto.PhoneDTO;
import com.guilherme.Agenda.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/contacts")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping
    public ResponseEntity<List<Contact>> findAll(){
        List<Contact> list = contactService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Contact> findById(@PathVariable Long id){
        Contact object = contactService.findById(id);
        return ResponseEntity.ok().body(object);
    }

    @GetMapping(value = "/firstName/{firstName}")
    public ResponseEntity<Contact> findByFirstName(@PathVariable String firstName){
        Contact object = contactService.findByFirstName(firstName);
        return ResponseEntity.ok().body(object);
    }

    @GetMapping(value = "/lastName/{lastName}")
    public ResponseEntity<Contact> findByLastName(@PathVariable String lastName){
        Contact object = contactService.findByLastName(lastName);
        return ResponseEntity.ok().body(object);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAll(){
        contactService.deleteAll();
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id){
        contactService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Contact> addContact(@Valid @RequestBody Contact object){
        try {
            Contact newContact = contactService.addContact(object);
            return ResponseEntity.ok(newContact);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CREATED).body(object);
        }
    }

    @PostMapping("/{id}/phone-add")
    public ResponseEntity<Contact> addPhoneToCOntact(@PathVariable Long contactId,
                                                     @RequestBody PhoneDTO phoneDTO) {
        try {
            Contact updatedContact = contactService.addPhoneToContact(contactId, phoneDTO);
            return new ResponseEntity<>(updatedContact, HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/address-add")
    public ResponseEntity<Contact> addAddressToCOntact(@PathVariable Long contactId,
                                                     @RequestBody AddressDTO addressDTO) {
        try {
            Contact updatedContact = contactService.addAddressToContact(contactId, addressDTO);
            return new ResponseEntity<>(updatedContact, HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public ResponseEntity<Void> update(@Valid @RequestBody ContactDTO contactDTO, @PathVariable Long id){
        Contact contact = contactService.fromDTO(contactDTO);
        contact.setId(id);
        contact = contactService.updateContact(contact);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Contact> updateContactWithPhoneAndAddress(
            @PathVariable("id") Long contactId,
            @RequestBody ContactNewDTO contactNewDTO) {
        try {
            Contact updatedContact = contactService.updateContactWithPhoneAndAddress(contactId, contactNewDTO);
            return new ResponseEntity<>(updatedContact, HttpStatus.OK);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/pages")
    public ResponseEntity<List<Contact>> showAllContacts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Contact> contactPage = contactService.showAllContacts(pageable);
        List<Contact> contacts = contactPage.getContent();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }
}
