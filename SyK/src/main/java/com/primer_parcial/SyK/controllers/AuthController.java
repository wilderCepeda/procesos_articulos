package com.primer_parcial.SyK.controllers;

import com.primer_parcial.SyK.models.UserModel;
import com.primer_parcial.SyK.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "auth/login")
    public ResponseEntity login(@RequestBody UserModel user){

        return userService.login(user.getEmail(), user.getPassword());

    }


}
