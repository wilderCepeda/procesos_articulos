package com.parcialUno.merchandises.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
@Table(name = "merchandises")
@Entity
public class MerchandiseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "merchandise_id")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @Column(name = "article_code", length = 100, nullable = false, unique = true)
    private String code;
    @Column(name = "article_name", length = 300, nullable = false)
    private String name;
    @Column(name = "article_description", length = 300, nullable = false)
    private String description;
    @Column(name = "article_stock", nullable = false)
    private Integer stock;
    @Column(name = "article_sale_price", nullable = false)
    private String salePrice;
    @Column(name = "article_purchase_price", nullable = false)
    private String purchasePrice;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date registerDate;

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Long getArticleId() {
        return id;
    }

    public void setArticleId(Long merchandiseId) {
        this.id = merchandiseId;
    }

    public String getArticleCode() {
        return code;
    }

    public void setArticleCode(String articleCode) {
        this.code = articleCode;
    }

    public String getArticleName() {
        return name;
    }

    public void setArticleName(String articleName) {
        this.name = articleName;
    }

    public String getArticleDescription() {
        return description;
    }

    public void setArticleDescription(String articleDescription) {
        this.description = articleDescription;
    }

    public Integer getArticleStock() {
        return stock;
    }

    public void setArticleStock(Integer articleStock) {
        this.stock = articleStock;
    }


    public String getArticleSalePrice() {
        return salePrice;
    }

    public void setArticleSalePrice(String articleSalePrice) {
        this.salePrice = articleSalePrice;
    }

    public String getArticlePurchasePrice() {
        return purchasePrice;
    }

    public void setArticlePurchasePrice(String articlePurchasePrice) {
        this.purchasePrice = articlePurchasePrice;
    }

    public Date getCreatedAt() { return registerDate; }

    public void setCreatedAt(Date registerDate) {
        this.registerDate = registerDate;
    }
}
