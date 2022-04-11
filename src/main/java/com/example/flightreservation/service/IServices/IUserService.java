package com.example.flightreservation.service.IServices;


import com.example.flightreservation.entity.User;

import java.util.List;


public interface IUserService {

    User createUser(User user);
    List<User>viewUsers();
    User viewUserById(Long id);
    User updateUserById(User user,Long id);
    void deleteUserById(Long id);



}
