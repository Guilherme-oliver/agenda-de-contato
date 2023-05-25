package com.guilherme.Agenda.services.validation;

import com.guilherme.Agenda.controller.exceptions.FieldMessage;
import com.guilherme.Agenda.model.Contact;
import com.guilherme.Agenda.model.dto.ContactNewDTO;
import com.guilherme.Agenda.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactInsertValidation implements ConstraintValidator<ContactInsert, ContactNewDTO> {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void initialize(ContactInsert ann) {}

    @Override
    public boolean isValid(ContactNewDTO contactNewDTO, ConstraintValidatorContext context){
        List<FieldMessage> list = new ArrayList<>();

        Optional<Contact> aux = contactRepository.findByFirstName(contactNewDTO.getFirstName());
        if (aux != null){
            list.add(new FieldMessage("Name", " Contact already exist!"));
        }

        for (FieldMessage e : list){
           context.disableDefaultConstraintViolation();
           context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                   .addConstraintViolation();
        }
        return list.isEmpty();
    }

}
