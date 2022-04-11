package com.example.flightreservation.controller;
import com.example.flightreservation.entity.User;
import com.example.flightreservation.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/view")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<User> viewUsers() {
        return userService.viewUsers();
    }

    @GetMapping("/view/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<User>viewUserById(@PathVariable Long id){
       return new ResponseEntity<User>(userService.viewUserById(id),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<User>updateUserBYId(@PathVariable("id") Long id,@RequestBody User user){
        return new ResponseEntity<User>(userService.updateUserById(user,id),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<String>deleteUserById(@PathVariable("id") Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<String>("User Deleted",HttpStatus.OK);
    }




}
