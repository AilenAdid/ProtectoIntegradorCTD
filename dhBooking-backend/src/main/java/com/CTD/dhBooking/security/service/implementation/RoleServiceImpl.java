package com.CTD.dhBooking.security.service.implementation;

import com.CTD.dhBooking.security.dto.RoleDTO;
import com.CTD.dhBooking.exceptions.BadRequestException;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import com.CTD.dhBooking.security.repository.RoleRepository;
import com.CTD.dhBooking.security.entities.Role;
import com.CTD.dhBooking.security.service.interfaces.IRoleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    public static final Logger logger = Logger.getLogger(RoleServiceImpl.class);
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        List<RoleDTO> rolesDTO = new ArrayList<>();
        for (Role r : roles) {
            rolesDTO.add(mapper.convertValue(r, RoleDTO.class));
        }
        return rolesDTO;
    }

    @Override
    public RoleDTO getRoleById(Integer id) throws ResourceNotFoundException {
        Optional<Role> roleSearched = roleRepository.findById(id);
        if (roleSearched.isEmpty()) {
            logger.error ("========= ID:"+ id + " not found.");
            throw new ResourceNotFoundException("Role #" + id + " not found.");
        }
        return mapper.convertValue(roleSearched.get(), RoleDTO.class);
    }

    @Override
    public RoleDTO updateRole(RoleDTO role) throws BadRequestException, ResourceNotFoundException {
        if (isRoleBlank(role)) {
            logger.error("========= Role incomplete.");
            throw new BadRequestException("Role must have all it's fields completed.");
        } else if (!roleRepository.existsById(role.getId())) {
            logger.error ("========= ID:"+ role.getId() + " not found.");
            throw new ResourceNotFoundException("Role #" + role.getId() + " not found. Can not be updated.");
        }
        Role role2 = mapper.convertValue(role, Role.class);
        return mapper.convertValue(roleRepository.save(role2), RoleDTO.class);
    }

    @Override
    public void deleteRole(Integer id) throws ResourceNotFoundException {
        if (!roleRepository.existsById(id)) {
            logger.error ("========= ID:"+ id + " not found.");
            throw new ResourceNotFoundException("Role #" + id + " not found. Can not be deleted.");
        }
        roleRepository.deleteById(id);
    }

    @Override
    public RoleDTO addRole(RoleDTO role) throws BadRequestException {
        if (isRoleBlank(role)) {
            logger.error("========= Role incomplete.");
            throw new BadRequestException("Role must have all it's fields completed.");
        }
        Role role2 = mapper.convertValue(role, Role.class);
        return mapper.convertValue(roleRepository.save(role2), RoleDTO.class);
    }

    private boolean isRoleBlank(RoleDTO role) {
        return role == null || role.getName().isBlank();
    }
}
