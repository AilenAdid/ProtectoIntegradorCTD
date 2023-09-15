package com.CTD.dhBooking.controller;
import com.CTD.dhBooking.dto.AddressDTOAdd;
import com.CTD.dhBooking.dto.AddressDTOResponse;
import com.CTD.dhBooking.dto.AddressDTOUpdate;
import com.CTD.dhBooking.exceptions.BadRequestException;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import com.CTD.dhBooking.service.implementation.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/addresses")
@CrossOrigin(origins = "*")
public class AddressController {
    @Autowired
    private AddressServiceImpl addressService;

    @GetMapping
    public ResponseEntity<Set<AddressDTOResponse>> listAddresses(){
        return ResponseEntity.ok(addressService.listAddresses());
    }

    @PostMapping
    public ResponseEntity<AddressDTOResponse> addAddress (@Valid @RequestBody AddressDTOAdd address) throws BadRequestException {
        return ResponseEntity.ok(addressService.addAddress(address));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTOResponse> searchAddress(@PathVariable Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(addressService.searchAddressById(id));
    }

    @PutMapping
    public ResponseEntity<AddressDTOResponse> updateAddress (@Valid @RequestBody AddressDTOUpdate address) throws ResourceNotFoundException, BadRequestException {
        return ResponseEntity.ok(addressService.updateAddress(address));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id) throws ResourceNotFoundException {
        addressService.deleteAddress(id);
        return ResponseEntity.ok().build();
    }
}
