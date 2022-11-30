package com.example.portalberita.Controller;

import com.example.portalberita.DTO.InsertCategoryDTO;
import com.example.portalberita.Entity.Category;
import com.example.portalberita.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/v1/admin/category")
    public ResponseEntity createCategory(@RequestBody InsertCategoryDTO insertCategory){
        categoryService.save(insertCategory);
        return new ResponseEntity("success", HttpStatus.OK);
    }


    @GetMapping("/v1/category")
    public Map<String, Category[]> getCategory(){
        Map<String, Category[]> ress = new HashMap<>();

        List<Category> list = categoryService.getCategory();

        Category[] strings = list.toArray(Category[]::new);
        ress.put("category", strings);
        return ress;
    }
}
