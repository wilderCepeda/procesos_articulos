package com.primer_parcial.Merchandise.controllers;

import com.primer_parcial.Merchandise.models.Usuario;
import com.primer_parcial.Merchandise.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 3600)
@RestController
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping(value = "auth/login")
    public ResponseEntity login(@RequestBody Usuario usuario){

        return usuarioService.login(usuario.getCorreo(), usuario.getPassword());

    }


}
