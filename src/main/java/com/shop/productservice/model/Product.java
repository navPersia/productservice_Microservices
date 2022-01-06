package com.shop.productservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String name;
    private String imgUrl;
    private String description;
    private Boolean active;
    private String userId;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public Product() {
    }

    public Product(String name, String imgUrl, String description, Boolean active, String userId) {
        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
        this.active = active;
        this.userId = userId;
    }

    public Product(String id, String name, String imgUrl, String description, Boolean active, String userId) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
        this.active = active;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
