package com.shayancse.SecurityApp.module6SecurityApplication.services;

import com.shayancse.SecurityApp.module6SecurityApplication.dto.SignUpDTO;
import com.shayancse.SecurityApp.module6SecurityApplication.dto.UserDTO;
import com.shayancse.SecurityApp.module6SecurityApplication.entities.User;
import com.shayancse.SecurityApp.module6SecurityApplication.exceptions.ResourceNotFoundException;
import com.shayancse.SecurityApp.module6SecurityApplication.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("User with email " + username + " not found"));
    }


    public UserDTO signUp(SignUpDTO signUpDTO) {
        Optional<User> user = userRepository.findByEmail(signUpDTO.getEmail());
        if (user.isPresent()) {
            throw new BadCredentialsException("User with email already exists" + signUpDTO.getEmail());
        }

        //If the user is not present then create a new user
        User toBeCreatedUser = modelMapper.map(signUpDTO, User.class); //map the user DTO class to a entity class
        toBeCreatedUser.setPassword(passwordEncoder.encode(toBeCreatedUser.getPassword()));

        User savedUser = userRepository.save(toBeCreatedUser);
        return modelMapper.map(savedUser, UserDTO.class);
    }



    //Finding the user with the help of userId
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with id " + userId + " not found"));
    }
}


















