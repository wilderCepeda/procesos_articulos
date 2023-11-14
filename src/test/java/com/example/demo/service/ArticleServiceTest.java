package com.example.demo.service;

import com.example.demo.article.entity.Article;
import com.example.demo.article.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;

    @Test
    void existService() {
        assertNotNull(articleService);
    }

    @Test
    void getAll() {
        List<Article> articles = articleService.getAll();
        assertThat(articles).isEmpty();
    }

}
