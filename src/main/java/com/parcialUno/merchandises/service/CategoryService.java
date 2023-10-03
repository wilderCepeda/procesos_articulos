package com.parcialUno.merchandises.service;

import com.parcialUno.merchandises.model.CategoryModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    private static List<CategoryModel> categories = new ArrayList<>();

    public CategoryModel getCategoryById(Long id) {
        return categories.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public CategoryModel createCategory(CategoryModel category) {
        category.setId((long) categories.size() + 1);
        categories.add(category);
        return category;
    }

    public void deleteCategory(Long id) {
        CategoryModel category = getCategoryById(id);
        if (category!= null) {
            categories.remove(category);
        }
    }

    public List<CategoryModel> getAllCategories() {
        return categories;
    }
}
