package com.guilherme.Agenda.services.validation;

import com.guilherme.Agenda.controller.exceptions.FieldMessage;
import com.guilherme.Agenda.model.Contact;
import com.guilherme.Agenda.model.dto.ContactDTO;
import com.guilherme.Agenda.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.HandlerMapping;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContactUpdateInsert implements ConstraintValidator<ContactUpdate, ContactDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public void initialize(ContactUpdate ann){}

    @Override
    public boolean isValid(ContactDTO contactDTO, ConstraintValidatorContext context){

        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Contact aux = contactRepository.findByFirstName(contactDTO.getFirstName());
        if (aux != null && aux.equals(uriId)) {
            list.add(new FieldMessage("Name", " Name already exist!"));
        }

        for (FieldMessage e : list) {
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }

}
