package com.example.BlogApplication.DTOs.Response;

import java.util.List;

public class CategoryResponse {
    private Integer id;
    private String category;

    public CategoryResponse() {
        super();
    }

    public CategoryResponse(Integer id, String category) {
        this.id = id;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
