package com.example.demo.user.controller;

import com.example.demo.category.entity.Category;
import com.example.demo.user.dto.UserRequest;
import com.example.demo.user.entity.User;
import com.example.demo.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("api/user")
    public ResponseEntity<?> save(@RequestBody UserRequest userRequest) {
        try {
            User userCreate = this.userService.save(userRequest);
            return new ResponseEntity<>(userCreate, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
