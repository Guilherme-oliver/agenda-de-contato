package com.guilherme.Agenda.services;

import com.guilherme.Agenda.model.Address;
import com.guilherme.Agenda.repositories.AddressRepository;
import com.guilherme.Agenda.services.exceptions.DatabaseException;
import com.guilherme.Agenda.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

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

    public Address insert(Address address){
        return addressRepository.save(address);
    }

    private void updateData(Address address, Address obj){
        address.setCep(obj.getCep());
        address.setPublicPlaceOrRoad(obj.getPublicPlaceOrRoad());
        address.setNumber(obj.getNumber());
        address.setCity(obj.getCity());
        address.setState(obj.getState());
    }

    public Address update(Address address){
        Address newAddress = findById(address.getId());
        updateData(newAddress, address);
        return addressRepository.save(newAddress);
    }

}
