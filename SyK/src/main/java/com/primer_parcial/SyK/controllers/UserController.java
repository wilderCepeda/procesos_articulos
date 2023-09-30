package com.primer_parcial.SyK.controllers;

import com.primer_parcial.SyK.models.UserModel;
import com.primer_parcial.SyK.repository.UserRepository;
import com.primer_parcial.SyK.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(maxAge = 3600)
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    //--------------------------------------------Crear usuario--------------------------------------------
    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody UserModel user){

        return userService.createUser(user);

    }
}
