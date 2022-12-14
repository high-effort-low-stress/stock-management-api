package com.github.hels.stockmanagement.service;


import com.github.hels.stockmanagement.exceptions.ApiException;
import com.github.hels.stockmanagement.model.Category;
import com.github.hels.stockmanagement.repository.ICategoryRepository;
import com.github.hels.stockmanagement.repository.input.ListCategoryInput;
import com.github.hels.stockmanagement.repository.specification.ListCategorySpecification;
import com.github.hels.stockmanagement.repository.specification.Operator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateCategoryService {
    private final ICategoryRepository repository;

    public Category execute(String name, String parentUuid) {
        Category category = new Category();

        if (parentUuid != null){
            category.setParent(findParent(parentUuid));
        }

        validateDuplication(name, parentUuid);

        category.setUuid(UUID.randomUUID().toString());
        category.setName(name);

        return repository.save(category);
    }

    private Category findParent(String uuid) {
        return repository
                .findByUuid(uuid)
                .orElseThrow(() -> new ApiException("Parent category not found"));
    }

    private void validateDuplication(String name, String parentUuid) {
        ListCategoryInput input = new ListCategoryInput();
        input.addRootField("name", Operator.EQUAL, name)
                .addParentField("uuid", Operator.EQUAL, parentUuid);
        List<Category> categories = repository.findAll(new ListCategorySpecification(input));

        if (!categories.isEmpty())
            throw new ApiException("A category with same name already exists");
    }
}
