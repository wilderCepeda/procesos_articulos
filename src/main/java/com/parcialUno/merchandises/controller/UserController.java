package com.parcialUno.merchandises.controller;

import com.parcialUno.merchandises.model.UserModel;
import com.parcialUno.merchandises.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("users/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("users")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user){
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("users/{id}")
    public ResponseEntity<UserModel> updateUser(@RequestBody UserModel user, @PathVariable Long id){
        return new ResponseEntity<>(userService.updateUser(user, id), HttpStatus.OK);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        return new ResponseEntity(userService.deleteUser(id), HttpStatus.NO_CONTENT);
    }

    @GetMapping("users")
    public  ResponseEntity<List<UserModel>> findAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }
}
