package com.parcialUno.tienda.controller;

import com.parcialUno.tienda.model.Articulo;
import com.parcialUno.tienda.service.ArticuloService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    //endpoint GET para "obtener por ID"
    @GetMapping("articulos/{id}")
    public ResponseEntity<Articulo> getAriculoById(@PathVariable Long id){
        return ResponseEntity.ok(articuloService.getArticuloById(id));
    }


    @PostMapping("articulos/{id}")
    public ResponseEntity<Articulo> create(@Valid @RequestBody Articulo articulo, @PathVariable Long id)
    {
        return new ResponseEntity<>(articuloService.createArticulo(articulo, id), HttpStatus.CREATED);
    }

    // endpoint DELETE para "borrar por ID"
    @DeleteMapping("articulos/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity(articuloService.deleteArticulo(id), HttpStatus.NO_CONTENT);
    }

    // enpoint PUT para "actualizar por ID"
    @PutMapping("articulos/{id}")
    public ResponseEntity<Articulo> update(@Valid @RequestBody Articulo articulo,
                                           @PathVariable Long id,
                                           @RequestParam Long idCategory){
        return new ResponseEntity<>(articuloService.updateArticulo(articulo,id, idCategory),HttpStatus.OK);
    }

    // enpoint GET para "obtener la lista completa de articulos"
    @GetMapping("articulos")
    public ResponseEntity <List<Articulo>> findAll(){
        return ResponseEntity.ok(articuloService.findAllArticulos());
    }

}
