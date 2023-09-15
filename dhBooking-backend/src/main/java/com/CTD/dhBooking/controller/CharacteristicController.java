package com.CTD.dhBooking.controller;

import com.CTD.dhBooking.dto.CategoryDTOAdd;
import com.CTD.dhBooking.dto.CategoryDTOResponse;
import com.CTD.dhBooking.dto.CharacteristicDTOAdd;
import com.CTD.dhBooking.dto.CharacteristicDTOResponse;
import com.CTD.dhBooking.exceptions.BadRequestException;
import com.CTD.dhBooking.service.implementation.CategoryServiceImpl;
import com.CTD.dhBooking.service.implementation.CharacteristicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/characteristics")
@CrossOrigin(origins = "*")
public class CharacteristicController {
   @Autowired
   private CharacteristicServiceImpl characteristicService;

   @GetMapping
   public ResponseEntity<Set<CharacteristicDTOResponse>> listCharacteristics() {
      return ResponseEntity.ok(characteristicService.listCharacteristics());
   }

   @PostMapping
   public ResponseEntity<CharacteristicDTOResponse> addCharacteristic(@Valid @RequestBody CharacteristicDTOAdd characteristic) throws BadRequestException {
      return ResponseEntity.ok(characteristicService.addCharacteristic(characteristic));
   }

}