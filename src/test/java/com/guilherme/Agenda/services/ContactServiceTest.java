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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

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

        when(contactRepository.save(Mockito.any(Contact.class))).thenReturn(contact);

        Contact savedContact = contactService.addContact(contact);

        assertNotNull(savedContact);
        assertEquals("John", savedContact.getFirstName());
        assertEquals("Hilton", savedContact.getLastName());
    }

    @Test
    public void testFindAllContacts() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact(null,"John", "Hilton"));
        contacts.add(new Contact(null, "John", "Smith"));

        when(contactRepository.findAll()).thenReturn(contacts);

        List<Contact> allContacts = contactService.findAll();

        assertNotNull(allContacts);
        assertEquals(2, allContacts.size());
    }

    @Test
    void testFindById_ContactExists() {
        Long contactId = 1L;
        Contact contact = new Contact();
        contact.setId(contactId);

        when(contactRepository.findById(contactId)).thenReturn(Optional.of(contact));

        Contact result = contactService.findById(contactId);

        assertEquals(contact, result);
        verify(contactRepository, times(1)).findById(contactId);
    }

    @Test
    void testFindById_ContactDoesNotExist() {
        Long contactId = 1L;

        when(contactRepository.findById(contactId)).thenReturn(Optional.empty());

        Contact result = contactService.findById(contactId);

        assertEquals(null, result);
        verify(contactRepository, times(1)).findById(contactId);
    }

    @Test
    void testUpdateContact() {
        Long contactId = 1L;
        Contact existingContact = new Contact();
        existingContact.setId(contactId);
        existingContact.setFirstName("Eduardo");
        existingContact.setLastName("Tutu");

        Contact updatedContact = new Contact();
        updatedContact.setId(contactId);
        updatedContact.setFirstName("Leonardo");
        updatedContact.setLastName("Leo");

        when(contactRepository.findById(contactId)).thenReturn(Optional.of(existingContact));
        when(contactRepository.save(any(Contact.class))).thenReturn(updatedContact);

        Contact result = contactService.updateContact(updatedContact);

        assertNotNull(result);
        assertEquals(contactId, result.getId());
        assertEquals("Leonardo", result.getFirstName());
        assertEquals("Leo", result.getLastName());

        verify(contactRepository, times(1)).findById(contactId);
        verify(contactRepository, times(1)).save(any(Contact.class));
    }
}