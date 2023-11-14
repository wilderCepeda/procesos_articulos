package com.example.demo.article.service;

import com.example.demo.article.entity.Article;
import com.example.demo.article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
//implementacion de la carpera de service para los articulos
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public List<Article> getAll() {
        return this.articleRepository.findAll();
    }

    public Optional<Article> findById(Long id) {
        return this.articleRepository.findById(id);
    }

    public Optional<Article> findByCode(String code) {
        return this.articleRepository.findByArticleCode(code);
    }

    public Article save(Article article) {
        return this.articleRepository.save(article);
    }

    public void delete(Article article) {
        this.articleRepository.delete(article);
    }
}
