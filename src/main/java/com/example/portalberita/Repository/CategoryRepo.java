package com.example.portalberita.Repository;

import com.example.portalberita.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category, String> {

    Category findByName(String name);

}
