package com.example.BlogApplication.Services;

import com.example.BlogApplication.DTOs.Request.CategoryRequest;
import com.example.BlogApplication.DTOs.Response.CategoryResponse;
import com.example.BlogApplication.Entities.Category;
import com.example.BlogApplication.Exceptions.ResourceNotFoundException;

import java.util.List;

public interface CategoryService {
    public Category addCategory(CategoryRequest obj);

    public Category addCategory(Category obj);

    public Category updateCategory(CategoryRequest obj, Integer categoryId) throws ResourceNotFoundException;

    public Category updateCategory(Category obj, Integer categoryId) throws ResourceNotFoundException;

    public void deleteCategory(Integer categoryId) throws ResourceNotFoundException;
    public Category getOneCategory(Integer categoryId) throws ResourceNotFoundException;
    public List<Category> getAllCategory();

    public Category requestToCategory(CategoryRequest obj);

    public CategoryRequest categoryToRequest(Category obj);
    public CategoryResponse categoryToResponse(Category obj);
    public Category responseToCategory(CategoryResponse obj) throws ResourceNotFoundException;
}
