package com.github.hels.stockmanagement.repository;

import com.github.hels.stockmanagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ICategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByUuid(String uuid);

    Optional<Category> findByNameAndParentUuid(String name, String parentUuid);

    Optional<Category> findByName(String name);
}
