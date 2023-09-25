package com.example.BlogApplication.Services;

import com.example.BlogApplication.DTOs.Request.CategoryRequest;
import com.example.BlogApplication.DTOs.Response.CategoryResponse;
import com.example.BlogApplication.DTOs.Response.PostResponse;
import com.example.BlogApplication.Entities.Category;
import com.example.BlogApplication.Entities.Posts;
import com.example.BlogApplication.Exceptions.ResourceNotFoundException;
import com.example.BlogApplication.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private PostsService postsService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category addCategory(CategoryRequest obj) {
        Category category = this.requestToCategory(obj);
        this.categoryRepository.save(category);
        return category;
    }

    @Override
    public Category addCategory(Category obj) {
        return this.categoryRepository.save(obj);
    }

    @Override
    public Category updateCategory(CategoryRequest obj, Integer categoryId) throws ResourceNotFoundException {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(categoryId + " categoryId is not found from Category","Category","CategoryId",categoryId));
        category.setId(obj.getId());
        category.setCategory(obj.getCategory());
        return category;
    }

    @Override
    public Category updateCategory(Category obj, Integer categoryId) throws ResourceNotFoundException {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(categoryId + " categoryId is not found from Category","Category","CategoryId",categoryId));
        category.setId(obj.getId());
        category.setCategory(obj.getCategory());
        category.setPosts(obj.getPosts());
        return category;
    }

    @Override
    public void deleteCategory(Integer categoryId) throws ResourceNotFoundException {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(categoryId + " categoryId is not found from Category","Category","CategoryId",categoryId));
        this.categoryRepository.delete(category);
    }

    @Override
    public Category getOneCategory(Integer categoryId) throws ResourceNotFoundException {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException(categoryId + " categoryId is not found from Category","Category","CategoryId",categoryId));
        return category;
    }

    @Override
    public List<Category> getAllCategory() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Category requestToCategory(CategoryRequest obj) {
        Category category = new Category();
        category.setId(obj.getId());
        category.setCategory(obj.getCategory());
        return category;
    }

    @Override
    public CategoryRequest categoryToRequest(Category obj) {
        CategoryRequest request = new CategoryRequest();
        request.setId(obj.getId());
        request.setCategory(obj.getCategory());
        return request;
    }

    @Override
    public CategoryResponse categoryToResponse(Category obj) {
        CategoryResponse response = new CategoryResponse();
        response.setId(obj.getId());
        response.setCategory(obj.getCategory());
        return response;
    }

    @Override
    public Category responseToCategory(CategoryResponse obj) throws ResourceNotFoundException {
        Category category = new Category();
        category.setId(obj.getId());
        category.setCategory(obj.getCategory());
        return category;
    }
}
