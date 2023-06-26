package com.guilherme.Agenda.services;

import com.guilherme.Agenda.model.Phone;
import com.guilherme.Agenda.repositories.PhoneRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PhoneServiceTest {

    @Mock
    private PhoneRepository phoneRepository;

    @InjectMocks
    private PhoneService phoneService;

    @Test
    public void testAddPhoneToContact() {
        Phone phone = new Phone();
        phone.setDdd(34);
        phone.setNumberPhone(987789987);
        phone.setContact(null);

        Mockito.when(phoneRepository.save(Mockito.any(Phone.class))).thenReturn(phone);

        Phone savePhone = phoneService.savePhone(phone);

        assertNotNull(savePhone);
        assertEquals(34, savePhone.getDdd());
        assertEquals(987789987, savePhone.getNumberPhone());
    }

    @Test
    public void testGetAllPhone() {
        List<Phone> phones = new ArrayList<>();
        phones.add(new Phone(null, 34, 456456, null));
        phones.add(new Phone(null, 37, 789789, null));

        Mockito.when(phoneRepository.findAll()).thenReturn(phones);

        List<Phone> allNumbers = phoneService.findAll();

        assertNotNull(allNumbers);
        assertEquals(2, allNumbers.size());
    }

    @Test
    public void testUpdatePhone() {

    }

    @Test
    public void testRemovePhoneFromContact() {

    }


}
