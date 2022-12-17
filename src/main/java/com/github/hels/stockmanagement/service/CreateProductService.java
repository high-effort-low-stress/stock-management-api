package com.github.hels.stockmanagement.service;

import com.github.hels.stockmanagement.exceptions.ApiException;
import com.github.hels.stockmanagement.model.Category;
import com.github.hels.stockmanagement.model.Product;
import com.github.hels.stockmanagement.repository.ICategoryRepository;
import com.github.hels.stockmanagement.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CreateProductService {
    private final IProductRepository repository;
    private final ICategoryRepository category;
    public Product execute(String name, String description,Integer quantity, String categoryUuid) {
        Product product = new Product();

        validateDuplication(name);
        product.setName(name);
        product.setDescription(description);
        product.setQuantity(quantity);
        product.setUuid(UUID.randomUUID().toString());
        product.setCategories(getCategory(categoryUuid));

        return repository.save(product);

    }
    private void validateDuplication(String name) {
        repository.findByName(name).ifPresent(el -> {
            throw new ApiException("A product with same name already exists");
        });
    }

    private Set<Category> getCategory(String uuid) {
        Category c = category.findByUuid(uuid)
                .orElseThrow(() -> new ApiException("Category does not exists"));
        return Set.of(c);
    }
}
