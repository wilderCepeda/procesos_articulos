package com.primer_parcial.Merchandise.services;

import com.primer_parcial.Merchandise.models.Categoria;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoriaService {
    ResponseEntity<Categoria> createCategoria(Categoria categoria);
    ResponseEntity<Categoria> editCategoria(Long id,Categoria categoria);
    ResponseEntity<Categoria> deleteCategoria(Long id);
    ResponseEntity<List<Categoria>> getAllCategorias();
    ResponseEntity <Categoria>getCategoriaById(Long id);
}
