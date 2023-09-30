package com.primer_parcial.SyK.services;

import com.primer_parcial.SyK.data.FactoryMerchandiseTestData;
import com.primer_parcial.SyK.data.FactoryCategoryTestData;
import com.primer_parcial.SyK.data.FactoryMerchandiseTestData;
import com.primer_parcial.SyK.models.MerchandiseModel;
import com.primer_parcial.SyK.models.CategoryModel;
import com.primer_parcial.SyK.repository.MerchandiseRepository;
import com.primer_parcial.SyK.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class ArticuloServiceImplTest {

    @InjectMocks
    private MerchandiseServiceImpl merchandiseService;

    @Mock
    private MerchandiseRepository merchandiseRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @DisplayName("Test para obtener articulos por codigo")
    @Test
    void GetMerchandiseByCodTest() {

        //Given
        MerchandiseModel merchandise = FactoryMerchandiseTestData.mockMerchandise();

        //When
        when(merchandiseRepository.findByCode(anyString())).thenReturn(Optional.of(merchandise));
        ResponseEntity<?> response = merchandiseService.getMerchandiseByCod(merchandise.getCode());

        //Then
        Assertions.assertNotNull(response);

    }

    @DisplayName("Test para listar a los Articulos")
    @Test
    void getAllMerchandisesTest() {

        //Given
        MerchandiseModel merchandise = FactoryMerchandiseTestData.mockMerchandise();

        //When

        ResponseEntity<List<MerchandiseModel>> list = merchandiseService.getAllMerchandises();

        //Then
        Assertions.assertNotNull(list);
    }

    @DisplayName("Test para crear Articulo")
    @Test
    void createMerchandiseTest() {
        //Given
        MerchandiseModel merchandise = FactoryMerchandiseTestData.mockMerchandise();
        given(merchandiseRepository.findByCode(merchandise.getCode())).willReturn(Optional.of(merchandise));
        given(merchandiseRepository.save(merchandise)).willReturn(merchandise);
        //When

        ResponseEntity<MerchandiseModel> merchandiseSaved = merchandiseService.createMerchandise(merchandise);

        //Then
        Assertions.assertNotNull(merchandiseSaved);
    }

    @DisplayName("Test para editar un Articulo")
    @Test
    void editMerchandiseTest() {
        // Given
        MerchandiseModel merchandise = FactoryMerchandiseTestData.mockMerchandise();
        MerchandiseModel merchandiseMod = FactoryMerchandiseTestData.mockMerchandiseMod();
        given(merchandiseRepository.findByCode(merchandise.getCode())).willReturn(Optional.of(merchandise));
        given(merchandiseRepository.save(merchandiseMod)).willReturn(merchandiseMod);

        //when

        ResponseEntity<MerchandiseModel> merchandiseSaved = merchandiseService
                .editMerchandise(merchandise.getCode(), merchandiseMod);

        //Then
        Assertions.assertNotNull(merchandiseSaved);
    }

    @DisplayName("Test para eliminar un Articulo")
    @Test
    void deleteArticleTest() {
        //Given
        MerchandiseModel merchandise = FactoryMerchandiseTestData.mockMerchandise();

        given(merchandiseRepository.findByCode(merchandise.getCode())).willReturn(Optional.of(merchandise));
        merchandiseRepository.deleteById(merchandise.getId());

        //when
        Optional<MerchandiseModel> elmMerchandise = merchandiseRepository.findById(merchandise.getId());

        //Then
        assertThat(elmMerchandise).isEmpty();
    }

    @Test
    @DisplayName("Test para una lista vacia")
    void listVoidMerchandise() {
        when(merchandiseRepository.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity<?> mockArticleService = merchandiseService.getAllMerchandises();

        Assertions.assertNotNull(mockArticleService);
        Assertions.assertEquals( 404, mockArticleService.getStatusCodeValue());
    }

    @DisplayName("Test para Cuando se retorna un bad request")
    @Test
    void createArticleReturnBadRequest() {
        MerchandiseModel mockMerchandiseModel = null;
        CategoryModel mockCategoryModel = FactoryCategoryTestData.mockCategory();

        when(categoryRepository.findById(mockCategoryModel.getId())).thenReturn(Optional.empty());
        when(merchandiseRepository.save(any(MerchandiseModel.class))).thenThrow(new NullPointerException());

        Assertions.assertThrows(NullPointerException.class, () ->{
            merchandiseService.createMerchandise(mockMerchandiseModel);
        });
    }
}