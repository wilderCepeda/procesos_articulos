package com.example.demo.article.entity;

import com.example.demo.category.entity.Category;
import com.example.demo.user.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long articleId;

    @Column(name = "article_code", length = 100,nullable = false, unique = true)
    private String articleCode;

    @Column(name = "article_name",length = 300,nullable = false)
    private String articleName;

    @Column(name = "article_description",length = 300,nullable = false)
    private String articleDescription;

    @Column(name = "article_stock",nullable = false)
    private Integer articleStock;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "article_sale_price",nullable = false)
    private Double articleSalePrice;

    @Column(name = "article_purchase_price",nullable = false)
    private Double articlePurchasePrice;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getArticleCode() {
        return articleCode;
    }

    public void setArticleCode(String articleCode) {
        this.articleCode = articleCode;
    }

    public String getArticleName() {
        return articleName;
    }

    public void setArticleName(String articleName) {
        this.articleName = articleName;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public Integer getArticleStock() {
        return articleStock;
    }

    public void setArticleStock(Integer articleStock) {
        this.articleStock = articleStock;
    }

    public Category getCategory() {
        return category;
    }


    public void setCategory(Category category) {
        this.category = category;
    }


    public Double getArticleSalePrice() {
        return articleSalePrice;
    }

    public void setArticleSalePrice(Double articleSalePrice) {
        this.articleSalePrice = articleSalePrice;
    }

    public Double getArticlePurchasePrice() {
        return articlePurchasePrice;
    }

    public void setArticlePurchasePrice(Double articlePurchasePrice) {
        this.articlePurchasePrice = articlePurchasePrice;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
