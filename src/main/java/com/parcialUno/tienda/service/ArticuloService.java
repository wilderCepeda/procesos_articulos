package com.parcialUno.tienda.service;


import com.parcialUno.tienda.exception.NotFoundException;
import com.parcialUno.tienda.model.Articulo;
import com.parcialUno.tienda.model.Categoria;
import com.parcialUno.tienda.repository.ArticuloRepository;
import com.parcialUno.tienda.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ArticuloService {

    @Autowired
    private ArticuloRepository articuloRepository;

    @Autowired
    @Lazy
    private CategoriaService categoriaService;

    // metodo listar articulos por ID
    public Articulo getArticuloById(Long id){
        if(id == null){
            throw new NotFoundException(Constants.ARTICLE_IS_NULL.getMessage());
        }
        Optional<Articulo> articulo=articuloRepository.findById(id);
        if(articulo.isEmpty()){
            throw new NotFoundException(Constants.ARTICLE_NOT_FOUND.getMessage());

        }
        return articuloRepository.findById(id).get();
    }

    public Articulo createArticulo(Articulo articulo, Long idCategory)
    {
        Categoria categoria = categoriaService.getCategoriaById(idCategory);
        articulo.setCategoria(categoria);
        return articuloRepository.save(articulo);
    }


    //metodo para eliminar articulos
    public boolean deleteArticulo(Long id){
        Optional<Articulo> articuloBd=articuloRepository.findById(id);
        if(articuloBd.isEmpty()){
            throw new NotFoundException(Constants.ARTICLE_NOT_FOUND.getMessage());
        }
        articuloRepository.delete(articuloBd.get());
        return true;
    }

    // metodo para actualizar articulos
    public Articulo updateArticulo(Articulo articuloReq, Long id){
        Optional<Articulo> articuloBd=articuloRepository.findById(id);
        if(articuloBd.isEmpty()){
            throw new NotFoundException(Constants.ARTICLE_NOT_FOUND.getMessage());
        }
        articuloBd.get().setName(articuloReq.getName());
        articuloBd.get().setPrice(articuloReq.getPrice());
        articuloBd.get().setDescription(articuloReq.getDescription());
        articuloBd.get().setStock(articuloReq.getStock());
        
        return articuloRepository.save(articuloBd.get());

    }

    // metodo para listar todos los articulos

    public List<Articulo> findAllArticulos(){
        return (List<Articulo>) articuloRepository.findAll();
    }

}
