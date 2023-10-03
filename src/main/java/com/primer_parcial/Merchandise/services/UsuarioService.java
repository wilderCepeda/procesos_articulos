package com.primer_parcial.Merchandise.services;

import com.primer_parcial.Merchandise.models.Usuario;
import org.springframework.http.ResponseEntity;
public interface UsuarioService {
    ResponseEntity<Usuario> createUser(Usuario usuario);

    ResponseEntity login(String correo, String password);
}
