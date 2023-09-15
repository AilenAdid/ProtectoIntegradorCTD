package com.CTD.dhBooking.controller;
import com.CTD.dhBooking.dto.CityDTOAdd;
import com.CTD.dhBooking.dto.CityDTOResponse;
import com.CTD.dhBooking.exceptions.BadRequestException;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import com.CTD.dhBooking.service.implementation.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/cities")
@CrossOrigin(origins = "*")
public class CityController {
   @Autowired
   private CityServiceImpl cityService;

   @GetMapping
   public ResponseEntity<Set<CityDTOResponse>> listCities() {
      return ResponseEntity.ok(cityService.listCities());
   }

   @GetMapping("/{id}")
   public ResponseEntity<CityDTOResponse> searchCity(@PathVariable Integer id) throws ResourceNotFoundException {
      return ResponseEntity.ok(cityService.searchCityById(id));
   }

   @PostMapping
   public ResponseEntity<CityDTOResponse> addCity(@Valid @RequestBody CityDTOAdd city) throws BadRequestException {
      return ResponseEntity.ok(cityService.addCity(city));
   }
}