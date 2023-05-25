package com.guilherme.Agenda.controller;

import com.guilherme.Agenda.model.Contact;
import com.guilherme.Agenda.model.dto.ContactDTO;
import com.guilherme.Agenda.model.dto.ContactNewDTO;
import com.guilherme.Agenda.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @GetMapping(value = "/firstName")
    public ResponseEntity<Optional<Contact>> findByFirstName(@PathVariable String firstName){
        Optional<Contact> object = contactService.findByFirstName(firstName);
        return ResponseEntity.ok().body(object);
    }

    @GetMapping(value = "/lastName")
    public ResponseEntity<Optional<Contact>> findByLastName(@PathVariable String lastName){
        Optional<Contact> object = contactService.findByLastName(lastName);
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
    public ResponseEntity<Contact> save(@Valid @RequestBody ContactNewDTO object){
        Contact contact = contactService.fromNewDTO(object);
        contact = contactService.insert(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PutMapping
    public ResponseEntity<Void> update(@Valid @RequestBody ContactDTO contactDTO, @PathVariable Long id){
        Contact contact = contactService.fromDTO(contactDTO);
        contact.setId(id);
        contact = contactService.update(contact);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/page/contacts")
    public ResponseEntity<Page> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Contact> list = contactService.findPage(page, linesPerPage, orderBy, direction);
        return ResponseEntity.ok().body(list);
    }

}
