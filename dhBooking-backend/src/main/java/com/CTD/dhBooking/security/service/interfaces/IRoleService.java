package com.CTD.dhBooking.security.service.interfaces;
import com.CTD.dhBooking.security.dto.RoleDTO;
import com.CTD.dhBooking.exceptions.BadRequestException;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import com.CTD.dhBooking.security.entities.Role;

import java.util.List;

public interface IRoleService {
    List<RoleDTO> getAllRoles();
    RoleDTO getRoleById(Integer id) throws ResourceNotFoundException;
    RoleDTO updateRole(RoleDTO role) throws BadRequestException, ResourceNotFoundException;
    void deleteRole(Integer id) throws ResourceNotFoundException;
    RoleDTO addRole(RoleDTO role) throws BadRequestException;
}
