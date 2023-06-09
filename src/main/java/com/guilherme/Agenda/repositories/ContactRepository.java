package com.guilherme.Agenda.repositories;

import com.guilherme.Agenda.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Contact findByFirstName(String firstName);
    Contact findByLastName(String lastName);
}
