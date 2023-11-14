package com.example.demo.article.controller;

import com.example.demo.article.entity.Article;
import com.example.demo.article.service.ArticleService;
import com.example.demo.auth.service.AuthService;
import com.example.demo.category.service.CategoryService;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ArticleController {

    private final ArticleService articleService;
    private final CategoryService categoryService;
    private final AuthService authService;


    public ArticleController(ArticleService articleService, CategoryService categoryService , AuthService authService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
        this.authService = authService;
    }

    @GetMapping("/article")
    public ResponseEntity<List<Article>> getAll() {
        return new ResponseEntity<>(this.articleService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/article/{code}")
    public ResponseEntity<?> getByCode(@PathVariable String code) {
        Optional<Article> article = this.articleService.findByCode(code);
        if (article.isPresent()) {
            return new ResponseEntity<>(article.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/article")
    public ResponseEntity<?> save(@RequestBody Article article) {
        try {
            if (categoryService.existById(article.getCategory().getCategoryId())){
                article.setUser(this.authService.getUserAuthenticated());
                Article articleSave = this.articleService.save(article);
                return new ResponseEntity<>(articleSave, HttpStatus.CREATED);
            }
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("La categoría no existe");
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/article/{code}")
    public ResponseEntity<?> update(@PathVariable String code, @RequestBody Article article ) {
        try {
            Optional<Article> articleDb = this.articleService.findByCode(code);
            if (articleDb.isPresent()) {
                articleDb.get().setArticleName(article.getArticleName());
                articleDb.get().setArticleDescription(article.getArticleDescription());
                articleDb.get().setArticleStock(article.getArticleStock());
                articleDb.get().setCategory(article.getCategory());
                articleDb.get().setArticleSalePrice(article.getArticleSalePrice());
                articleDb.get().setArticlePurchasePrice(article.getArticlePurchasePrice());
                Article articleUpdate = articleService.save(articleDb.get());
                return new ResponseEntity<>(articleUpdate, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/article/{code}")
    public ResponseEntity<?> delete(@PathVariable String code){
        Optional<Article> articleDb = articleService.findByCode(code);
        if(articleDb.isPresent()){
            articleService.delete(articleDb.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
