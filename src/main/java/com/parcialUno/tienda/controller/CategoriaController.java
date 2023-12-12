package com.parcialUno.tienda.controller;

import com.parcialUno.tienda.model.Categoria;
import com.parcialUno.tienda.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class CategoriaController
{
    @Autowired
    //Instancia del REPOSITORIO
    private CategoriaService categoriaService;

    //EndPoint GET para "Obtener por ID"
    @GetMapping("categorias/{idCategory}")
    public ResponseEntity getCategoriaById(@PathVariable Long idCategory)

    {
        return ResponseEntity.ok(categoriaService.getCategoriaById(idCategory));
    }

    // endpoint POST para "enviar valores"
    @PostMapping("categorias")
    public ResponseEntity<Categoria> create(@Valid @RequestBody Categoria categoria){
        return new ResponseEntity<>(categoriaService.createCategoria(categoria), HttpStatus.CREATED);
    }

    //EndPoint PUT para "Actualizar por ID"
    @PutMapping("categorias/{idCategory}")
    public ResponseEntity<Categoria> update(@Valid @RequestBody Categoria categoria, @PathVariable Long idCategory)
    {
        return new ResponseEntity<>(categoriaService.updateCategoria(categoria, idCategory),HttpStatus.OK);
    }

    //EndPoint DELETE para "Borrar por ID"
    @DeleteMapping("categorias/{idCategory}")
    public ResponseEntity<String> delete(@PathVariable Long idCategory)
    {
        return new ResponseEntity(categoriaService.deleteCategoria(idCategory), HttpStatus.NO_CONTENT);
    }

    //EndPoint GET para "Listar ALL"
    @GetMapping("categorias")
    public ResponseEntity getCategoriaAll()
    {
        return ResponseEntity.ok(categoriaService.getCategoriaAll());
    }
}
