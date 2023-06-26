package com.guilherme.Agenda.services;

import com.guilherme.Agenda.model.Contact;
import com.guilherme.Agenda.repositories.ContactRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class ContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;

    @Test
    public void testAddContact() {
        Contact contact = new Contact();
        contact.setFirstName("John");
        contact.setLastName("Hilton");

        Mockito.when(contactRepository.save(Mockito.any(Contact.class))).thenReturn(contact);

        Contact savedContact = contactService.addContact(contact);

        assertNotNull(savedContact);
        assertEquals("John", savedContact.getFirstName());
        assertEquals("Hilton", savedContact.getLastName());
    }

    @Test
    public void testGetAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact(null,"John", "Hilton"));
        contacts.add(new Contact(null, "John", "Smith"));

        Mockito.when(contactRepository.findAll()).thenReturn(contacts);

        List<Contact> allContacts = contactService.findAll();

        assertNotNull(allContacts);
        assertEquals(2, allContacts.size());
    }
}
