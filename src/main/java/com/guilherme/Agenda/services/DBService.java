package com.guilherme.Agenda.services;

import com.guilherme.Agenda.model.Address;
import com.guilherme.Agenda.model.Contact;
import com.guilherme.Agenda.model.Phone;
import com.guilherme.Agenda.repositories.AddressRepository;
import com.guilherme.Agenda.repositories.ContactRepository;
import com.guilherme.Agenda.repositories.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    ContactRepository contactRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    AddressRepository addressRepository;

    public void instantiateTestDatabase() throws ParseException {

        Contact contact1 = new Contact(null, "Bill", "Snow");
        Contact contact2 = new Contact(null, "Prof", "Oak");

        contactRepository.saveAll(Arrays.asList(contact1, contact2));

        Phone phone1 = new Phone(null,11, 989898, contact1);
        Phone phone2 = new Phone(null,34, 845874, contact2);

        phoneRepository.saveAll(Arrays.asList(phone1, phone2));

        Address address1 = new Address(null, 33333358L, "Rua Pomodoro", 333, "Uberlândia", "MG", contact1);
        Address address2 = new Address(null, 85858585L, "Joao Pedro Carvalho", 505, "Uberaba","MG", contact2);

        addressRepository.saveAll(Arrays.asList(address1, address2));

        contact1.getPhones().add(phone1);
        contact1.getAddresses().add(address1);

        contact2.getPhones().add(phone2);
        contact2.getAddresses().add(address2);

        contactRepository.saveAll(Arrays.asList(contact1, contact2));
    }

}
