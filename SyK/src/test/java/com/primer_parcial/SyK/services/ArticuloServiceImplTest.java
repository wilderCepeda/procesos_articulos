package com.primer_parcial.SyK.services;

import com.primer_parcial.SyK.data.FactoryArticuloTestData;
import com.primer_parcial.SyK.data.FactoryCtaegoriaTestData;
import com.primer_parcial.SyK.models.Articulo;
import com.primer_parcial.SyK.models.Categoria;
import com.primer_parcial.SyK.repository.ArticuloRepository;
import com.primer_parcial.SyK.repository.CategoriaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.ResourceAccessException;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ArticuloServiceImplTest {

    @InjectMocks
    private ArticuloServiceImpl articuloService;

    @Mock
    private ArticuloRepository articuloRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @DisplayName("Test para obtener articulos por codigo")
    @Test
    void GetArticleByCodTest() {

        //Given
        Articulo articulo = FactoryArticuloTestData.mockArticulo();

        //When
        when(articuloRepository.findByCodigo(anyString())).thenReturn(Optional.of(articulo));
        ResponseEntity<Articulo> respuesta = articuloService.getArticleByCod(articulo.getCodigo());

        //Then
        Assertions.assertNotNull(respuesta);

    }

    @DisplayName("Test para listar a los Articulos")
    @Test
    void getAllArticlesTest() {

        //Given
        Articulo articulo = FactoryArticuloTestData.mockArticulo();

        //When

        ResponseEntity<List<Articulo>> lista = articuloService.getAllArticles();

        //Then
        Assertions.assertNotNull(lista);
    }

    @DisplayName("Test para crear Articulo")
    @Test
    void createArticleTest() {
        //Given
        Articulo articulo = FactoryArticuloTestData.mockArticulo();
        given(articuloRepository.findByCodigo(articulo.getCodigo())).willReturn(Optional.of(articulo));
        given(articuloRepository.save(articulo)).willReturn(articulo);
        //When

        ResponseEntity<Articulo> articuloGuardado = articuloService.createArticle(articulo);

        //Then
        Assertions.assertNotNull(articuloGuardado);
    }

    @DisplayName("Test para editar un Articulo")
    @Test
    void editArticleTest() {
        // Given
        Articulo articulo = FactoryArticuloTestData.mockArticulo();
        Articulo articuloMod = FactoryArticuloTestData.mockArticuloMod();
        given(articuloRepository.findByCodigo(articulo.getCodigo())).willReturn(Optional.of(articulo));
        given(articuloRepository.save(articuloMod)).willReturn(articuloMod);

        //when

        ResponseEntity<Articulo> articuloGuardado = articuloService.editArticle(articulo.getCodigo(), articuloMod);

        //Then
        Assertions.assertNotNull(articuloGuardado);
    }

    @DisplayName("Test para eliminar un Articulo")
    @Test
    void deleteArticleTest() {
        //Given
        Articulo articulo = FactoryArticuloTestData.mockArticulo();

        given(articuloRepository.findByCodigo(articulo.getCodigo())).willReturn(Optional.of(articulo));
        articuloRepository.deleteById(articulo.getId());

        //when
        Optional<Articulo> elmArticulo = articuloRepository.findById(articulo.getId());

        //Then
        assertThat(elmArticulo).isEmpty();
    }

    @Test
    @DisplayName("Test para una lista vacia")
    void listaArticulosVacia() {
        when(articuloRepository.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity mockArticleService = articuloService.getAllArticles();

        Assertions.assertNotNull(mockArticleService);
        Assertions.assertEquals( 404, mockArticleService.getStatusCodeValue());
    }

    @DisplayName("Test para Cuando se retorna un bad request")
    @Test
    void createArticleReturnBadRequest() {
        Articulo mockArticleModel = null;
        Categoria mockCategoryModel = FactoryCtaegoriaTestData.mockCategoria();

        when(categoriaRepository.findById(mockCategoryModel.getId())).thenReturn(Optional.empty());
        when(articuloRepository.save(any(Articulo.class))).thenThrow(new NullPointerException());

        Assertions.assertThrows(NullPointerException.class, () ->{
            articuloService.createArticle(mockArticleModel);
        });
    }
}