package com.CTD.dhBooking.controller;
import com.CTD.dhBooking.dto.ReservationDTOAdd;
import com.CTD.dhBooking.dto.ReservationDTOResponse;
import com.CTD.dhBooking.dto.ReservationDTOUpdate;
import com.CTD.dhBooking.exceptions.BadRequestException;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import com.CTD.dhBooking.service.implementation.ReservationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/reservations")
@CrossOrigin(origins = "*")
public class ReservationController {
    @Autowired
    ReservationServiceImpl reservationService;

    @GetMapping
    public List<ReservationDTOResponse> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ReservationDTOResponse getReservationById(@PathVariable Integer id) throws ResourceNotFoundException {
        return reservationService.getReservationById(id);
    }

    @PostMapping
    public ResponseEntity<ReservationDTOResponse> addReservation(@Valid @RequestBody ReservationDTOAdd reservation) throws BadRequestException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/reservations").toUriString());
        return ResponseEntity.created(uri).body(reservationService.addReservation(reservation));

    }

    @PutMapping
    public ResponseEntity<ReservationDTOResponse> updateReservation(@Valid @RequestBody ReservationDTOUpdate reservation) throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.ok(reservationService.updateReservation(reservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable Integer id) throws ResourceNotFoundException {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/searchByUser")
    public ResponseEntity<List<ReservationDTOResponse>> getAllReservationsByUserId(@RequestParam Integer id) {
        return ResponseEntity.ok(reservationService.getAllByUserId(id));
    }
}
