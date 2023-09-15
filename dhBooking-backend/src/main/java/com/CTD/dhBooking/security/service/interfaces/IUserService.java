package com.CTD.dhBooking.security.service.interfaces;
import com.CTD.dhBooking.security.dto.UserDTOAdd;
import com.CTD.dhBooking.security.dto.UserDTOResponse;
import com.CTD.dhBooking.exceptions.BadRequestException;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import com.CTD.dhBooking.security.dto.UserDTOUpdate;
import com.CTD.dhBooking.security.entities.User;
import java.util.List;

public interface IUserService {
    List<UserDTOResponse> getAllUsers();
    UserDTOResponse getUserById(Integer id) throws ResourceNotFoundException;
    UserDTOResponse addUser(UserDTOAdd user) throws BadRequestException;
    UserDTOResponse updateUser(UserDTOUpdate user) throws ResourceNotFoundException;
    void deleteUser(Integer id) throws ResourceNotFoundException;
}
