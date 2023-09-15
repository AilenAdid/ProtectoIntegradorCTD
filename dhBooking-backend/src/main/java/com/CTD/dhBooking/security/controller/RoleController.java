package com.CTD.dhBooking.security.controller;
import com.CTD.dhBooking.security.dto.RoleDTO;
import com.CTD.dhBooking.exceptions.BadRequestException;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import com.CTD.dhBooking.security.entities.Role;
import com.CTD.dhBooking.security.service.implementation.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/roles")
@CrossOrigin(origins = "*")
public class RoleController {
    @Autowired
    private RoleServiceImpl roleService;

    @PostMapping
    public ResponseEntity<RoleDTO> addRole(@RequestBody RoleDTO role) throws BadRequestException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/roles").toUriString());
        return ResponseEntity.created(uri).body(roleService.addRole(role));
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> getRoleById(@PathVariable Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @PutMapping
    public ResponseEntity<RoleDTO> updateRole(@RequestBody RoleDTO role) throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.ok(roleService.updateRole(role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@PathVariable Integer id) throws ResourceNotFoundException {
        roleService.deleteRole(id);
        return ResponseEntity.ok().build();
    }
}
