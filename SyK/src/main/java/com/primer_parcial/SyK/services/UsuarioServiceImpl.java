package com.primer_parcial.SyK.services;

import com.primer_parcial.SyK.models.Usuario;
import com.primer_parcial.SyK.repository.UsuarioRepository;
import com.primer_parcial.SyK.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public ResponseEntity<Usuario> createUser(Usuario usuario) {
        try {
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            usuarioRepository.save(usuario);
            return new ResponseEntity(usuario, HttpStatus.CREATED);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity login(String correo, String password) {
        try{
            Usuario usuario = usuarioRepository.findByCorreo(correo);
            if(passwordEncoder.matches(password, usuario.getPassword())){
                String token = jwtUtil.create(String.valueOf(usuario.getId()), usuario.getCorreo());
                return ResponseEntity.ok(token);
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }

}

