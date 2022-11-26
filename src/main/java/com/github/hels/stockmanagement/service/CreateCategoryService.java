package com.github.hels.stockmanagement.service;


import com.github.hels.stockmanagement.exceptions.ApiException;
import com.github.hels.stockmanagement.model.Category;
import com.github.hels.stockmanagement.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateCategoryService {
    private final ICategoryRepository repository;

    public Category execute(String name, String parentUuid) {
        Category category = new Category();

        if (parentUuid != null){
            category.setParent(findParent(parentUuid));
            validateDuplication(name, parentUuid);
        }

        validateDuplication(name);
        category.setUuid(UUID.randomUUID().toString());
        category.setName(name);

        return repository.save(category);
    }

    private Category findParent(String uuid) {
        return repository
                .findByUuid(uuid)
                .orElseThrow(() -> new ApiException("Parent category not found"));
    }

    private void validateDuplication(String name) {
        repository.findByName(name).ifPresent(el -> {
            throw new ApiException("A category with same name already exists");
        });
    }

    private void validateDuplication(String name, String parentUuid) {
        repository.findByNameAndParentUuid(name, parentUuid).ifPresent(el -> {
            throw new ApiException("A category with same name already exists");
        });
    }
}
