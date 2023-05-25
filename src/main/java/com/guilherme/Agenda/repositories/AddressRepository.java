package com.guilherme.Agenda.repositories;

import com.guilherme.Agenda.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
