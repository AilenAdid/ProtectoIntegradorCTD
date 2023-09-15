package com.CTD.dhBooking.service.interfaces;
import com.CTD.dhBooking.dto.PolicyDTOAdd;
import com.CTD.dhBooking.dto.PolicyDTOResponse;
import com.CTD.dhBooking.dto.PolicyDTOUpdate;
import com.CTD.dhBooking.exceptions.BadRequestException;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import java.util.Set;

public interface IPolicyService {
    Set<PolicyDTOResponse> listPolicies();
    PolicyDTOResponse addPolicy(PolicyDTOAdd policy) throws BadRequestException;
    PolicyDTOResponse searchPolicyById(Integer id) throws ResourceNotFoundException;
    PolicyDTOResponse updatePolicy (PolicyDTOUpdate policy) throws ResourceNotFoundException, BadRequestException;
    void deletePolicy(Integer id) throws ResourceNotFoundException;
}
