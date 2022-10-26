package com.github.hels.stockmanagement.repository;

import com.github.hels.stockmanagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
   List<Category> findAll();

}
