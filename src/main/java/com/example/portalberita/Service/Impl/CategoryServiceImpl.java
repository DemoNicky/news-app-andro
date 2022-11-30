package com.example.portalberita.Service.Impl;

import com.example.portalberita.DTO.InsertCategoryDTO;
import com.example.portalberita.Entity.Category;
import com.example.portalberita.Repository.CategoryRepo;
import com.example.portalberita.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public void save(InsertCategoryDTO insertCategory) {
        Category category = new Category();
        category.setName(insertCategory.getName());
        categoryRepo.save(category);
    }

    @Override
    public List<Category> getCategory() {
        return categoryRepo.findAll();
    }
}
