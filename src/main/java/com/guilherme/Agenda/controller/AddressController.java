package com.guilherme.Agenda.controller;

import com.guilherme.Agenda.model.Address;
import com.guilherme.Agenda.services.AddressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<Address>> findAll(){
        List<Address> list = addressService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Address> findById(@RequestParam Long id){
        Address address = addressService.findById(id);
        return ResponseEntity.ok().body(address);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteById(@RequestParam Long id){
        addressService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Address> addAddress(@Valid @RequestBody Address address) {
        try {
            Address savedAddress = addressService.saveAddress(address);
            return new ResponseEntity<>(savedAddress, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/pages")
    public ResponseEntity<List<Address>> getAllContacts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Address> addressesPage = addressService.getAllContacts(pageable);
        List<Address> addresses = addressesPage.getContent();
        return new ResponseEntity<>(addresses, HttpStatus.OK);
    }
}
