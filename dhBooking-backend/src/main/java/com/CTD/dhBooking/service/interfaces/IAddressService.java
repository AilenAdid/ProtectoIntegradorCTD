package com.CTD.dhBooking.service.interfaces;
import com.CTD.dhBooking.dto.AddressDTOAdd;
import com.CTD.dhBooking.dto.AddressDTOResponse;
import com.CTD.dhBooking.dto.AddressDTOUpdate;
import com.CTD.dhBooking.exceptions.BadRequestException;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import java.util.Set;

public interface IAddressService {
    Set<AddressDTOResponse> listAddresses();
    AddressDTOResponse addAddress(AddressDTOAdd address) throws BadRequestException;
    AddressDTOResponse searchAddressById(Integer id) throws ResourceNotFoundException;
    AddressDTOResponse updateAddress(AddressDTOUpdate address) throws ResourceNotFoundException, BadRequestException;
    void deleteAddress(Integer id) throws ResourceNotFoundException;
}
