package com.CTD.dhBooking.security.service.implementation;

import com.CTD.dhBooking.security.dto.UserDTOAdd;
import com.CTD.dhBooking.security.dto.UserDTOResponse;
import com.CTD.dhBooking.exceptions.BadRequestException;
import com.CTD.dhBooking.exceptions.ResourceNotFoundException;
import com.CTD.dhBooking.security.dto.UserDTOUpdate;
import com.CTD.dhBooking.security.repository.UserRepository;
import com.CTD.dhBooking.security.entities.User;
import com.CTD.dhBooking.security.service.interfaces.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service @Transactional
public class UserServiceImpl implements IUserService, UserDetailsService {
    public static final Logger logger = Logger.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> searchedUser = userRepository.findByEmail(username);
        if (searchedUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found.");
        }
        return searchedUser.get();
    }

    @Override
    public List<UserDTOResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDTOResponse> usersDTO = new ArrayList<>();
        for (User u : users) {
            usersDTO.add(mapper.convertValue(u, UserDTOResponse.class));
        }
        return usersDTO;
    }

    @Override
    public UserDTOResponse getUserById(Integer id) throws ResourceNotFoundException {
        Optional<User> userSearched = userRepository.findById(id);
        if (userSearched.isEmpty()) {
            logger.error ("========= ID:"+ id + " not found.");
            throw new ResourceNotFoundException("User #" + id + " not found.");
        }
        return mapper.convertValue(userSearched.get(), UserDTOResponse.class);
    }

    @Override
    public UserDTOResponse addUser(UserDTOAdd user) throws BadRequestException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new BadRequestException("email already exits.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(mapper.convertValue(user, User.class));
        return mapper.convertValue(savedUser, UserDTOResponse.class);
    }

    @Override
    public UserDTOResponse updateUser(UserDTOUpdate user) throws ResourceNotFoundException {
        if (!userRepository.existsById(user.getId())) {
            logger.error ("========= ID:"+ user.getId() + " not found.");
            throw new ResourceNotFoundException("User #" + user.getId() + " not found. Can not be updated.");
        }
        User user2 = mapper.convertValue(user, User.class);
        return mapper.convertValue(userRepository.save(user2), UserDTOResponse.class);
    }

    @Override
    public void deleteUser(Integer id) throws ResourceNotFoundException {
        if (!userRepository.existsById(id)){
            logger.error ("========= ID:"+ id + " not found.");
            throw new ResourceNotFoundException("User #" + id + " not found. Can not be deleted.");
        }
        userRepository.deleteById(id);
    }
}
