package com.primer_parcial.SyK.controllers;


import com.primer_parcial.SyK.models.Articulo;
import com.primer_parcial.SyK.repository.ArticuloRepository;
import com.primer_parcial.SyK.repository.CategoriaRepository;
import com.primer_parcial.SyK.services.ArticuloService;
import com.primer_parcial.SyK.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(maxAge = 3600)
@RestController
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;
    @Autowired
    private ArticuloRepository articuloRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private JWTUtil jwtUtil;

    //--------------------------------------------Listar Articulo por codigo--------------------------------------------
    @GetMapping(value = "/articulo/codigo/{codigo}")
    public ResponseEntity getArticulo(@PathVariable String codigo, @RequestHeader(value = "Authorization") String token) {
        if (jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return articuloService.getArticleByCod(codigo);

    }


    //----------------------------------------------Listar todos los articulos------------------------------------------

    @GetMapping("/articulos")
    public ResponseEntity listarArticulo(@RequestHeader(value = "Authorization") String token){
        //if (jwtUtil.getKey(token)==null){
          //  return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        //}
        //return articuloService.getAllArticles();
    try {
        if (jwtUtil.getKey(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        }
        return articuloService.getAllArticles();
    }catch (Exception e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido. "+e.getMessage());
    }

    }

    //----------------------------------------------Crear un articulo---------------------------------------------------
    @PostMapping("/articulo")
    public ResponseEntity crearArticulo(@RequestBody Articulo articulo, @RequestHeader(value = "Authorization") String token){

        try {
            if (jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
            }
            return articuloService.createArticle(articulo);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido. "+e.getMessage());
        }

    }

    //--------------------------------------------Modificar un articulo-------------------------------------------------
    @PutMapping("/articulo/{codigo}")
    public ResponseEntity editarArticulo(@PathVariable String codigo, @RequestBody Articulo articulo , @RequestHeader(value = "Authorization") String token){

        try {
            if (jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
            }
            return articuloService.editArticle(codigo, articulo);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido"+e.getMessage());
        }

    }

    //------------------------------------------Eliminar un articulo----------------------------------------------------

    @DeleteMapping("articulo/{codigo}")
    public ResponseEntity eliminarArticulo(@PathVariable String codigo, @RequestHeader(value = "Authorization") String token){
        try {
            if (jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
            }
            return articuloService.deleteArticle(codigo);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido."+e.getMessage());
        }
    }


}

