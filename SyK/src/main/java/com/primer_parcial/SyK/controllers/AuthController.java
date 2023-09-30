package com.primer_parcial.SyK.controllers;

import com.primer_parcial.SyK.models.Usuario;
import com.primer_parcial.SyK.services.UsuarioService;
import com.primer_parcial.SyK.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
