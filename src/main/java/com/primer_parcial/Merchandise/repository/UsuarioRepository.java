package com.primer_parcial.Merchandise.repository;

import com.primer_parcial.Merchandise.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByCorreo(String correo);

}

