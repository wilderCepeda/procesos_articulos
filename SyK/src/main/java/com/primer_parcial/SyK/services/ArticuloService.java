package com.primer_parcial.SyK.services;

import com.primer_parcial.SyK.models.Articulo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ArticuloService {

    ResponseEntity<Articulo> getArticleByCod(String codigo);
    ResponseEntity<List<Articulo>> getAllArticles();
    ResponseEntity<Articulo> createArticle(Articulo articulo);
    ResponseEntity<Articulo> editArticle(String codigo, Articulo articulo);
    ResponseEntity<Articulo> deleteArticle(String codigo);


}
