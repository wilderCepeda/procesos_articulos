package com.primer_parcial.SyK.services;

import com.primer_parcial.SyK.models.CategoryModel;
import com.primer_parcial.SyK.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import java.util.Optional;
@Service

public class CategoryServicelmpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public ResponseEntity<CategoryModel> createCategory(CategoryModel category) {
            try {

                categoryRepository.save(category);
                return new ResponseEntity(category, HttpStatus.CREATED);
            } catch (Exception e) {
                System.out.print(e.fillInStackTrace());
                return ResponseEntity.badRequest().build();
            }

    }

    @Override
    public ResponseEntity<CategoryModel> editCategory(Long id, CategoryModel category) {
        Optional<CategoryModel> categoryBD = categoryRepository.findById(id);
        if (categoryBD.isPresent()) {
            try {
                categoryBD.get().setName(category.getName());
                categoryBD.get().setDescription(category.getDescription());
                categoryRepository.save(categoryBD.get());
                return new ResponseEntity(category, HttpStatus.OK);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @Override
    public ResponseEntity<CategoryModel> deleteCategory(Long id) {
        Optional<CategoryModel> categoryBD = categoryRepository.findById(id);
        if (categoryBD.isPresent()){
            categoryRepository.delete(categoryBD.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<List<CategoryModel>> getAllCategories() {
        List<CategoryModel> category = categoryRepository.findAll();
        if (category.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
    @Override
    public ResponseEntity getCategoryById(@PathVariable Long id) {
        Optional<CategoryModel> category = categoryRepository.findById(id);//Cambiar el Find ya que son todos los datos
        if (category.isPresent()) {
            return new ResponseEntity(category, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
}






