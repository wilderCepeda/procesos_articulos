package com.example.demo.category.service;

import com.example.demo.category.entity.Category;
import com.example.demo.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
//implementacion de la carpera de service para las categorias
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        return this.categoryRepository.findAll();
    }

    public Category save(Category category) {
        return this.categoryRepository.save(category);
    }

    public Boolean existById(Long id) {
        return this.categoryRepository.existsById(id);
    }

}
