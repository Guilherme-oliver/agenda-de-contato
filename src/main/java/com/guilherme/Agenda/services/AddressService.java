package com.guilherme.Agenda.services;

import com.guilherme.Agenda.model.Address;
import com.guilherme.Agenda.model.Contact;
import com.guilherme.Agenda.repositories.AddressRepository;
import com.guilherme.Agenda.services.exceptions.DatabaseException;
import com.guilherme.Agenda.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    public Address findById(Long id){
        Optional<Address> object = addressRepository.findById(id);
        return object.orElse(null);
    }

    public void deleteById(Long id) {
        try {
            addressRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public boolean isAddressNumberUniqueForContact(Address address) {
        Contact contact = address.getContact();
        List<Address> existingAddress = contact.getAddresses();
        return existingAddress.stream().noneMatch(p -> p.getPublicPlaceOrRoad().equals(address.getPublicPlaceOrRoad()));
    }

    public Address saveAddress(Address address) {
        if (isAddressNumberUniqueForContact(address)) {
            return addressRepository.save(address);
        } else {
            throw new IllegalArgumentException("Address already exists for the contact.");
        }
    }

    public Address updateAddress(Address updateAddress){
        Long addressId = updateAddress.getId();
        Address existingAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new IllegalArgumentException("Address already exist."));
        existingAddress.setCep(updateAddress.getCep());
        existingAddress.setPublicPlaceOrRoad(updateAddress.getPublicPlaceOrRoad());
        existingAddress.setNumber(updateAddress.getNumber());
        existingAddress.setCity(updateAddress.getCity());
        existingAddress.setState(updateAddress.getState());

        return addressRepository.save(existingAddress);
    }

    public Page<Address> getAllContacts(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }
}