package com.CTD.dhBooking.controller;
import com.CTD.dhBooking.dto.PolicyDTOAdd;
import com.CTD.dhBooking.dto.PolicyDTOResponse;
import com.CTD.dhBooking.dto.PolicyDTOUpdate;
import com.CTD.dhBooking.exceptions.BadRequestException;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import com.CTD.dhBooking.service.implementation.PolicyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/policies")
@CrossOrigin(origins = "*")
public class PolicyController {
    @Autowired
    private PolicyServiceImpl policyService;

    @GetMapping
    public ResponseEntity<Set<PolicyDTOResponse>> getPolicies() {
        return ResponseEntity.ok(policyService.listPolicies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolicyDTOResponse> getPolicy(@PathVariable Integer id) throws ResourceNotFoundException {
        return ResponseEntity.ok(policyService.searchPolicyById(id));
    }

    @PostMapping
    public ResponseEntity<PolicyDTOResponse> addPolicy(@Valid @RequestBody PolicyDTOAdd policy) throws BadRequestException {
        return ResponseEntity.ok(policyService.addPolicy(policy));
    }

    @PutMapping
    public ResponseEntity<PolicyDTOResponse> updatePolicy(@Valid @RequestBody PolicyDTOUpdate policy) throws BadRequestException, ResourceNotFoundException {
        return ResponseEntity.ok(policyService.updatePolicy(policy));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePolicy(@PathVariable Integer id) throws ResourceNotFoundException {
        policyService.deletePolicy(id);
        return ResponseEntity.ok().build();
    }
}
