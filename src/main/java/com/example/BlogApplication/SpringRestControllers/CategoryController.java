package com.example.BlogApplication.SpringRestControllers;

import com.example.BlogApplication.DTOs.Request.CategoryRequest;
import com.example.BlogApplication.DTOs.Response.ResponseAPI;
import com.example.BlogApplication.Entities.Category;
import com.example.BlogApplication.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addCategory")
    public ResponseEntity<ResponseAPI> addCategory(@RequestBody CategoryRequest request) {
        this.categoryService.addCategory(request);

        return new ResponseEntity<>(new ResponseAPI("Category added Successfully","true"), HttpStatus.OK);
    }

    @GetMapping("/getAllCategory")
    public List<Category> getAllCategories() {
        return this.categoryService.getAllCategory();
    }



}
