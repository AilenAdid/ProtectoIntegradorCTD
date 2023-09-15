package com.CTD.dhBooking.service.implementation;
import com.CTD.dhBooking.dto.PolicyDTOAdd;
import com.CTD.dhBooking.dto.PolicyDTOResponse;
import com.CTD.dhBooking.dto.PolicyDTOUpdate;
import com.CTD.dhBooking.entities.Policy;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import com.CTD.dhBooking.repository.PolicyRepository;
import com.CTD.dhBooking.service.interfaces.IPolicyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PolicyServiceImpl implements IPolicyService {
    public static final Logger logger = Logger.getLogger(PolicyServiceImpl.class);
    @Autowired
    private PolicyRepository policyRepository;
    @Autowired
    private ObjectMapper mapper;

    @Override
    public Set<PolicyDTOResponse> listPolicies() {
        List<Policy> policies = policyRepository.findAll();
        Set<PolicyDTOResponse> policiesDTO = new HashSet<>();
        for (Policy p : policies) {
            policiesDTO.add(mapper.convertValue(p, PolicyDTOResponse.class));
        }
        return policiesDTO;
    }

    @Override
    public PolicyDTOResponse addPolicy(PolicyDTOAdd policy) {
        Policy savedPolicy = policyRepository.save(mapper.convertValue(policy, Policy.class));
        return mapper.convertValue(savedPolicy, PolicyDTOResponse.class);
    }

    @Override
    public PolicyDTOResponse searchPolicyById(Integer id) throws ResourceNotFoundException {
        Optional<Policy> policy = policyRepository.findById(id);
        if (policy.isEmpty()) {
            logger.error ("========= ID:"+ id + " not found.");
            throw new ResourceNotFoundException("Policy #" + id + " was not found.");
        }
        return mapper.convertValue(policy, PolicyDTOResponse.class);
    }

    @Override
    public PolicyDTOResponse updatePolicy(PolicyDTOUpdate policy) throws ResourceNotFoundException {
        if (!policyRepository.existsById(policy.getId())) {
            logger.error ("========= ID:"+ policy.getId() + " not found.");
            throw new ResourceNotFoundException("Policy #" + policy.getId() + " not found. Can not be updated.");
        }
        policyRepository.save(mapper.convertValue(policy, Policy.class));
        return mapper.convertValue(policy, PolicyDTOResponse.class);
    }

    @Override
    public void deletePolicy(Integer id) throws ResourceNotFoundException {
        if (!policyRepository.existsById(id)) {
            logger.error ("========= ID:"+ id + " not found.");
            throw new ResourceNotFoundException("Policy #" + id + " not found. Can not be deleted.");
        }
        policyRepository.deleteById(id);
    }
}
