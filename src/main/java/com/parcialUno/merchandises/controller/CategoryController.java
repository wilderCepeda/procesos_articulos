package com.parcialUno.merchandises.controller;

import com.parcialUno.merchandises.model.CategoryModel;
import com.parcialUno.merchandises.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("categories/{id}")
    public ResponseEntity<CategoryModel> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping("categories")
    public ResponseEntity<CategoryModel> createCategory(@RequestBody CategoryModel category){
        CategoryModel createdCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(createdCategory);
    }

    @DeleteMapping("categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("categories")
    public ResponseEntity<List<CategoryModel>> getAllCategories(){
        List<CategoryModel> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }
}
