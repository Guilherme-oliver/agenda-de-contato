package com.guilherme.Agenda.services;

import com.guilherme.Agenda.model.Contact;
import com.guilherme.Agenda.model.Phone;
import com.guilherme.Agenda.repositories.PhoneRepository;
import com.guilherme.Agenda.services.exceptions.DatabaseException;
import com.guilherme.Agenda.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    public boolean isPhoneNumberUniqueForContact(Phone phone) {
        Contact contact = phone.getContact();
        List<Phone> existingPhones = contact.getPhones();
        return existingPhones.stream().noneMatch(p -> p.getNumberPhone().equals(phone.getNumberPhone()));
    }

    public Phone savePhone(Phone phone) {
        if (isPhoneNumberUniqueForContact(phone)) {
            return phoneRepository.save(phone);
        } else {
            throw new IllegalArgumentException("Phone number already exists for the contact.");
        }
    }

    public Phone updatePhone(Phone updatePhone){
        Long phoneId = updatePhone.getId();
        Phone existingPhone = phoneRepository.findById(phoneId)
                .orElseThrow(() -> new IllegalArgumentException("Phone number not found."));
        existingPhone.setDdd(updatePhone.getDdd());
        existingPhone.setNumberPhone(updatePhone.getNumberPhone());
        return phoneRepository.save(existingPhone);
    }

    public Page<Phone> getAllContacts(Pageable pageable) {
        return phoneRepository.findAll(pageable);
    }
}