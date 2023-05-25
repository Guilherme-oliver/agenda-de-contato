package com.guilherme.Agenda.repositories;

import com.guilherme.Agenda.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
