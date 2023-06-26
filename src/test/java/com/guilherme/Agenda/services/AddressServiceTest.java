package com.guilherme.Agenda.services;

import com.guilherme.Agenda.model.Address;
import com.guilherme.Agenda.repositories.AddressRepository;
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
public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressService addressService;

    @Test
    public void testAddressToContact() {

        Address address = new Address();
        address.setCep(38408228L);
        address.setPublicPlaceOrRoad("Jorge Martins");
        address.setNumber(696);
        address.setCity("Udi");
        address.setState("MG");

        Mockito.when(addressRepository.save(Mockito.any(Address.class))).thenReturn(address);

        Address saveAddress = addressService.saveAddress(address);

        assertNotNull(saveAddress);
        assertEquals(38408228L, saveAddress.getCep());
        assertEquals("Jorge Martins", saveAddress.getPublicPlaceOrRoad());
        assertEquals(696, saveAddress.getNumber());
        assertEquals("Udi", saveAddress.getCity());
        assertEquals("MG", saveAddress.getState());
    }

    @Test
    public void testListAddress() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address(null, 123456L, "Jorge Martins", 696, "Udi", "MG", null));
        addresses.add(new Address(null, 789456L, "Pedro Carvalho", 747, "Uberlandia", "MS", null));

        List<Address> addressList = addressService.findAll();

        assertNotNull(addressList);
        assertEquals(2, addressList.size());
    }

    @Test
    public void testUpdateAddress() {

    }

    @Test
    public void testRemoveAddressFromContact() {

    }
}
