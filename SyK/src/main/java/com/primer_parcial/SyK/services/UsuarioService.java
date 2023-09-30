package com.primer_parcial.SyK.services;

import com.primer_parcial.SyK.models.Usuario;
import org.springframework.http.ResponseEntity;
public interface UsuarioService {
    ResponseEntity<Usuario> createUser(Usuario usuario);

    ResponseEntity login(String correo, String password);
}
