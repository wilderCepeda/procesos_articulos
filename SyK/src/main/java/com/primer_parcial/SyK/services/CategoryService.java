package com.primer_parcial.SyK.services;

import com.primer_parcial.SyK.models.CategoryModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    ResponseEntity<CategoryModel> createCategory(CategoryModel category);
    ResponseEntity<CategoryModel> editCategory(Long id, CategoryModel category);
    ResponseEntity<CategoryModel> deleteCategory(Long id);
    ResponseEntity<List<CategoryModel>> getAllCategories();
    ResponseEntity <CategoryModel>getCategoryById(Long id);
}
