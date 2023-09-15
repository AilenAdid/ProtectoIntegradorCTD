package com.CTD.dhBooking.service.implementation;
import com.CTD.dhBooking.dto.ReservationDTOAdd;
import com.CTD.dhBooking.dto.ReservationDTOResponse;
import com.CTD.dhBooking.dto.ReservationDTOUpdate;
import com.CTD.dhBooking.entities.Reservation;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import com.CTD.dhBooking.repository.ReservationRepository;
import com.CTD.dhBooking.service.interfaces.IReservationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ReservationServiceImpl implements IReservationService {
    public static final Logger logger = Logger.getLogger(ReservationServiceImpl.class);
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public List<ReservationDTOResponse> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationDTOResponse> reservationsDTO = new ArrayList<>();
        for (Reservation r : reservations) {
            reservationsDTO.add(mapper.convertValue(r, ReservationDTOResponse.class));
        }
        return reservationsDTO;
    }

    @Override
    public ReservationDTOResponse getReservationById(Integer id) throws ResourceNotFoundException {
        Optional<Reservation> reservationSearched = reservationRepository.findById(id);
        if (reservationSearched.isEmpty()) {
            logger.error ("========= ID:"+ id + " not found.");
            throw new ResourceNotFoundException("Reservation #" + id + " not found.");
        }
        return mapper.convertValue(reservationSearched.get(), ReservationDTOResponse.class);
    }

    @Override
    public ReservationDTOResponse addReservation(ReservationDTOAdd reservation) {
        Reservation savedReservation = reservationRepository.save(mapper.convertValue(reservation, Reservation.class));
        return mapper.convertValue(savedReservation, ReservationDTOResponse.class);
    }

    @Override
    public ReservationDTOResponse updateReservation(ReservationDTOUpdate reservation) throws ResourceNotFoundException {
        if (!reservationRepository.existsById(reservation.getId())) {
            logger.error ("========= ID:"+ reservation.getId() + " not found.");
            throw new ResourceNotFoundException("Reservation #" + reservation.getId() + " not found. Can not be updated.");
        }
        reservationRepository.save(mapper.convertValue(reservation, Reservation.class));
        return mapper.convertValue(reservation, ReservationDTOResponse.class);
    }

    @Override
    public List<ReservationDTOResponse> getAllByUserId(Integer id) {
        List<Reservation> reservations = reservationRepository.findAllByUserId(id);
        List<ReservationDTOResponse> reservationsDTO = new ArrayList<>();
        for (Reservation r : reservations) {
            reservationsDTO.add(mapper.convertValue(r, ReservationDTOResponse.class));
        }
        return reservationsDTO;
    }

    @Override
    public void deleteReservation(Integer id) throws ResourceNotFoundException {
        if (!reservationRepository.existsById(id)) {
            logger.error ("========= ID:"+ id + " not found.");
            throw new ResourceNotFoundException("Reservation #" + id + " not found. Can not be deleted.");
        }
        reservationRepository.deleteById(id);
    }
}
