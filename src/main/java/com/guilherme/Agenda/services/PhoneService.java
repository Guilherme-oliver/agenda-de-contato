package com.guilherme.Agenda.services;

import com.guilherme.Agenda.model.Phone;
import com.guilherme.Agenda.repositories.PhoneRepository;
import com.guilherme.Agenda.services.exceptions.DatabaseException;
import com.guilherme.Agenda.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;

public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    public List<Phone> findAll(){
        return phoneRepository.findAll();
    }

    public Phone findById(Long id){
        Optional<Phone> object = phoneRepository.findById(id);
        return object.orElse(null);
    }

    public void deleteById(Long id) {
        try {
            phoneRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Phone insert(Phone phone){
        return phoneRepository.save(phone);
    }

    private void updateData(Phone phone, Phone obj){
        phone.setDdd(obj.getDdd());
        phone.setNumberPhone(obj.getNumberPhone());
    }

    public Phone update(Phone phone){
        Phone newPhone = findById(phone.getId());
        updateData(newPhone, phone);
        return phoneRepository.save(newPhone);
    }

}