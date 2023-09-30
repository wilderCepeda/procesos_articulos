package com.primer_parcial.SyK.controllers;

import com.primer_parcial.SyK.models.Usuario;
import com.primer_parcial.SyK.repository.UsuarioRepository;
import com.primer_parcial.SyK.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@CrossOrigin(maxAge = 3600)
@RestController
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    //--------------------------------------------Crear usuario--------------------------------------------
    @PostMapping("/usuario")
    public ResponseEntity crearUsuario(@RequestBody Usuario usuario){

        return usuarioService.createUser(usuario);

    }
}
