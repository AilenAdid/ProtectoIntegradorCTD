package com.CTD.dhBooking.service.implementation;
import com.CTD.dhBooking.dto.AddressDTOAdd;
import com.CTD.dhBooking.dto.AddressDTOResponse;
import com.CTD.dhBooking.dto.AddressDTOUpdate;
import com.CTD.dhBooking.entities.Address;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import com.CTD.dhBooking.repository.AddressRepository;
import com.CTD.dhBooking.service.interfaces.IAddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AddressServiceImpl implements IAddressService {
    public static final Logger logger = Logger.getLogger(AddressServiceImpl.class);
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public Set<AddressDTOResponse> listAddresses() {
        List<Address> addresses = addressRepository.findAll();
        Set<AddressDTOResponse> addressesDTO = new HashSet<>();
        for (Address address : addresses) {
            addressesDTO.add(mapper.convertValue(address, AddressDTOResponse.class));
        }
        return addressesDTO;
    }

    @Override
    public AddressDTOResponse addAddress(AddressDTOAdd address) {
        Address savedAddress = addressRepository.save(mapper.convertValue(address, Address.class));
        return mapper.convertValue(savedAddress, AddressDTOResponse.class);
    }

    @Override
    public AddressDTOResponse searchAddressById(Integer id) throws ResourceNotFoundException {
        Optional<Address> addressSearched = addressRepository.findById(id);
        if (addressSearched.isEmpty()) {
            logger.error("========= ID:"+ id + " not found.");
            throw new ResourceNotFoundException("Address #" + id + " not found.");
        }
        return mapper.convertValue(addressSearched.get(), AddressDTOResponse.class);
    }

    @Override
    public AddressDTOResponse updateAddress(AddressDTOUpdate address) throws ResourceNotFoundException {
        if (!addressRepository.existsById(address.getId())) {
            logger.error("========= ID:"+ address.getId() + " not found.");
            throw new ResourceNotFoundException("Address #" + address.getId() + " not found. Can not be updated.");
        }
        addressRepository.save(mapper.convertValue(address, Address.class));
        return mapper.convertValue(address, AddressDTOResponse.class);
    }

    @Override
    public void deleteAddress(Integer id) throws ResourceNotFoundException {
        if (!addressRepository.existsById(id)) {
            logger.error("========= ID:"+ id + " not found.");
            throw new ResourceNotFoundException("Address #" + id + " not found. Can not be deleted.");
        }
        addressRepository.deleteById(id);
    }
}
