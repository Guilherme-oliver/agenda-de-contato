package com.guilherme.Agenda.controller;

import com.guilherme.Agenda.model.Phone;
import com.guilherme.Agenda.services.PhoneService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/phone")
public class PhoneController {

    private PhoneService phoneService;

    public PhoneController(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @GetMapping
    public ResponseEntity<List<Phone>> findAll(){
        List<Phone> list = phoneService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Phone> findById(@RequestParam Long id){
        Phone phone = phoneService.findById(id);
        return ResponseEntity.ok().body(phone);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@RequestParam Long id){
        phoneService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Phone> addPhone(@Valid @RequestBody Phone phone) {
        try {
            Phone savedPhone = phoneService.savePhone(phone);
            return new ResponseEntity<>(savedPhone, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/pages")
    public ResponseEntity<List<Phone>> getAllPhones(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Phone> phonesPage = phoneService.getAllContacts(pageable);
        List<Phone> phones = phonesPage.getContent();
        return new ResponseEntity<>(phones, HttpStatus.OK);
    }
}