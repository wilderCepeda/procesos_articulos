package com.primer_parcial.SyK.controllers;


import com.primer_parcial.SyK.models.MerchandiseModel;
import com.primer_parcial.SyK.repository.MerchandiseRepository;
import com.primer_parcial.SyK.repository.CategoryRepository;
import com.primer_parcial.SyK.services.MerchandiseService;
import com.primer_parcial.SyK.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(maxAge = 3600)
@RestController
public class MerchandiseController {

    @Autowired
    private MerchandiseService merchandiseService;
    @Autowired
    private MerchandiseRepository merchandiseRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private JWTUtil jwtUtil;

    //--------------------------------------------Listar Articulo por codigo--------------------------------------------
    @GetMapping(value = "/articulo/codigo/{codigo}")
    public ResponseEntity getMerchandise(@PathVariable String code, @RequestHeader(value = "Authorization") String token) {
        if (jwtUtil.getKey(token)==null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token error");
        }
        return merchandiseService.getMerchandiseByCod(code);

    }


    //----------------------------------------------Listar todos los articulos------------------------------------------

    @GetMapping("/articulos")
    public ResponseEntity listMerchandise(@RequestHeader(value = "Authorization") String token){
        //if (jwtUtil.getKey(token)==null){
          //  return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no valido");
        //}
        //return articuloService.getAllArticles();
    try {
        if (jwtUtil.getKey(token) == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token error");
        }
        return merchandiseService.getAllMerchandises();
    }catch (Exception e){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token error "+e.getMessage());
    }

    }

    //----------------------------------------------Crear un articulo---------------------------------------------------
    @PostMapping("/articulo")
    public ResponseEntity createMerchandise(@RequestBody MerchandiseModel merchandise, @RequestHeader(value = "Authorization") String token){

        try {
            if (jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token error");
            }
            return merchandiseService.createMerchandise(merchandise);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token error "+e.getMessage());
        }

    }

    //--------------------------------------------Modificar un articulo-------------------------------------------------
    @PutMapping("/merchandise/{code}")
    public ResponseEntity editMerchandise(@PathVariable String code, @RequestBody MerchandiseModel merchandise , @RequestHeader(value = "Authorization") String token){

        try {
            if (jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token error");
            }
            return merchandiseService.editMerchandise(code, merchandise);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token error "+e.getMessage());
        }

    }

    //------------------------------------------Eliminar un articulo----------------------------------------------------

    @DeleteMapping("merchandise/{code}")
    public ResponseEntity<?> deleteMerchandise(@PathVariable String code, @RequestHeader(value = "Authorization") String token){
        try {
            if (jwtUtil.getKey(token) == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token error");
            }
            return merchandiseService.deleteMerchandise(code);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token error "+e.getMessage());
        }
    }


}

