package com.parcialUno.tienda.controller;


import com.parcialUno.tienda.model.User;
import com.parcialUno.tienda.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    //EndPoint GET para "Obtener por ID"
    @GetMapping("users/{id}")
    public ResponseEntity<User>  getUserById(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserById(id));
    }

    //EndPoint POST para "Enviar valores"
    @PostMapping("users")
    public ResponseEntity<User> create(@Valid @RequestBody User user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }



    //EndPoint PUT para "Actualizar por ID"
    @PutMapping("users/{id}")
    public ResponseEntity<User> update(@Valid @RequestBody User user, @PathVariable Long id){
        return new ResponseEntity<>(userService.updateUser(user,id), HttpStatus.OK);
    }
    //EndPoint DELETE para "Borrar por ID"
    @DeleteMapping("users/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity(userService.deleteUser(id), HttpStatus.NO_CONTENT);
    }
    //EndPoint GET para "Listar todos los USUARIOS"
    @GetMapping("users")
    public ResponseEntity<List<User>>  findAll(){
        return ResponseEntity.ok(userService.findAllUsers());
    }


}