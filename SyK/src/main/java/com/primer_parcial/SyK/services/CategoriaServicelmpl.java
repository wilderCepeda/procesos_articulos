package com.primer_parcial.SyK.services;

import com.primer_parcial.SyK.models.Articulo;
import com.primer_parcial.SyK.models.Categoria;
import com.primer_parcial.SyK.repository.CategoriaRepository;
import com.primer_parcial.SyK.repository.ArticuloRepository;
import com.primer_parcial.SyK.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import java.util.Optional;
@Service

public class CategoriaServicelmpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Override
    public ResponseEntity<Categoria> createCategoria(Categoria categoria) {
            try {

                categoriaRepository.save(categoria);
                return new ResponseEntity(categoria, HttpStatus.CREATED);
            } catch (Exception e) {
                System.out.print(e.fillInStackTrace());
                return ResponseEntity.badRequest().build();
            }

    }

    @Override
    public ResponseEntity<Categoria> editCategoria(Long id, Categoria categoria) {
        Optional<Categoria> categoriaBD = categoriaRepository.findById(id);
        if (categoriaBD.isPresent()) {
            try {
                categoriaBD.get().setNombre(categoria.getNombre());
                categoriaBD.get().setDescripcion(categoria.getDescripcion());
                categoriaRepository.save(categoriaBD.get());
                return new ResponseEntity(categoria, HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Categoria> deleteCategoria(Long id) {
        Optional<Categoria> categoriaBD = categoriaRepository.findById(id);
        if (categoriaBD.isPresent()){
            categoriaRepository.delete(categoriaBD.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<Categoria>> getAllCategorias() {
        List<Categoria> categoria = categoriaRepository.findAll();
        if (categoria.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(categoria, HttpStatus.OK);
    }
    @Override
    public ResponseEntity getCategoriaById(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaRepository.findById(id);//Cambiar el Find ya que son todos los datos
        if (categoria.isPresent()) {
            return new ResponseEntity(categoria, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
}






