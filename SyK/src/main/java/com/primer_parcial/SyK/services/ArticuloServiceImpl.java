package com.primer_parcial.SyK.services;

import com.primer_parcial.SyK.models.Articulo;
import com.primer_parcial.SyK.models.Categoria;
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
public class ArticuloServiceImpl implements ArticuloService{

    @Autowired
    private ArticuloRepository articuloRepository;


    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public ResponseEntity getArticleByCod(@PathVariable String codigo) {
        Optional<Articulo> articulo = articuloRepository.findAllByCodigo(codigo);//Cambiar el Find ya que son todos los datos
        if (articulo.isPresent()) {
            return new ResponseEntity(articulo, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<Articulo>> getAllArticles() {
        List<Articulo> articulo = articuloRepository.findAll();
        if (articulo.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity(articulo, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Articulo> createArticle(Articulo articulo) {
        Long idC = articulo.getCategoria().getId();
        Optional<Categoria> articuloBD = categoriaRepository.findById(idC);
        if (articuloBD.isPresent()) {
            try {

                articuloRepository.save(articulo);
                return new ResponseEntity(articulo, HttpStatus.CREATED);
            } catch (Exception e) {
                System.out.print(e.fillInStackTrace());
                return ResponseEntity.badRequest().build();
            }
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<Articulo> editArticle(String codigo, Articulo articulo) {
        Optional<Articulo> articuloBD = articuloRepository.findByCodigo(codigo);
        if (articuloBD.isPresent()) {
            try {
                articuloBD.get().setNombre(articulo.getNombre());
                articuloBD.get().setDescripcion(articulo.getDescripcion());
                articuloBD.get().setFechaRegistro(articulo.getFechaRegistro());
                articuloBD.get().setCategoria(articulo.getCategoria());
                articuloBD.get().setStock(articulo.getStock());
                articuloBD.get().setPrecio_venta(articulo.getPrecio_venta());
                articuloBD.get().setPrecio_compra(articulo.getPrecio_compra());
                articuloRepository.save(articuloBD.get());
                return new ResponseEntity(articulo, HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<Articulo> deleteArticle(String codigo) {
        Optional<Articulo> articuloBD = articuloRepository.findByCodigo(codigo);
        if (articuloBD.isPresent()){
            articuloRepository.delete(articuloBD.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
