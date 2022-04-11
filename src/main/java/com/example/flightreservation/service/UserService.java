package com.example.flightreservation.service;

import com.example.flightreservation.entity.User;
import com.example.flightreservation.exception.ResourceNotFoundException;
import com.example.flightreservation.repository.UserRepository;
import com.example.flightreservation.service.IServices.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService implements IUserService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User createUser(User user) {
        String encPwd = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encPwd);
        return userRepository.save(user);
    }


    @Override
    public List<User> viewUsers() {
        return userRepository.findAll();
    }

    @Override
    public User viewUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found"));
    }

    @Override
    public User updateUserById(User user, Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User not found"));

        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());

        userRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.findById(id).orElseThrow(() ->
                new RuntimeException("User not found"));

        userRepository.deleteById(id);
    }

}
