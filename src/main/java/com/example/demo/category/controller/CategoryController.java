package com.example.demo.category.controller;

import com.example.demo.article.entity.Article;
import com.example.demo.category.entity.Category;
import com.example.demo.category.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAll() {
        return new ResponseEntity<>(this.categoryService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/category")
    public ResponseEntity<?> save(@RequestBody Category category) {
        try {
            Category categorySave = this.categoryService.save(category);
            return new ResponseEntity<>(categorySave, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
