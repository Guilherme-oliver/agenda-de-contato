package com.guilherme.Agenda.repositories;

import com.guilherme.Agenda.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByFirstName(String firstName);
    Optional<Contact> findByLastName(String lastName);
}
