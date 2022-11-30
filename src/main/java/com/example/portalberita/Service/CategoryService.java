package com.example.portalberita.Service;

import com.example.portalberita.DTO.InsertCategoryDTO;
import com.example.portalberita.Entity.Category;

import java.util.List;

public interface CategoryService {
    void save(InsertCategoryDTO insertCategory);

    List<Category> getCategory();
}
