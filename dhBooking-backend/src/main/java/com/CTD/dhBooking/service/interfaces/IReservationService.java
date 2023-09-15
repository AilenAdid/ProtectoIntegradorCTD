package com.CTD.dhBooking.service.interfaces;
import com.CTD.dhBooking.dto.ReservationDTOAdd;
import com.CTD.dhBooking.dto.ReservationDTOResponse;
import com.CTD.dhBooking.dto.ReservationDTOUpdate;
import com.CTD.dhBooking.exceptions.BadRequestException;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import java.util.List;

public interface IReservationService {
    List<ReservationDTOResponse> getAllReservations();
    ReservationDTOResponse getReservationById(Integer id) throws ResourceNotFoundException;
    ReservationDTOResponse addReservation(ReservationDTOAdd reservation) throws BadRequestException;
    ReservationDTOResponse updateReservation(ReservationDTOUpdate reservation) throws BadRequestException, ResourceNotFoundException;
    void deleteReservation(Integer id) throws ResourceNotFoundException;
    List<ReservationDTOResponse> getAllByUserId(Integer id);
}
