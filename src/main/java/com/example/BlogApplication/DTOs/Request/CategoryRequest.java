package com.example.BlogApplication.DTOs.Request;

public class CategoryRequest {
    private Integer id;
    private String category;

    public CategoryRequest() {
        super();
    }

    public CategoryRequest(Integer id, String category) {
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
