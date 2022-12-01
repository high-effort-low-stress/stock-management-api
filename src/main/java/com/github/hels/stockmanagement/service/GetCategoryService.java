package com.github.hels.stockmanagement.service;

import com.github.hels.stockmanagement.exceptions.ApiException;
import com.github.hels.stockmanagement.model.Category;
import com.github.hels.stockmanagement.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCategoryService {
    private final ICategoryRepository repository;

    public Category execute(String uuid) {
        return repository
                .findByUuid(uuid)
                .orElseThrow(() -> new ApiException("Category not found"));
    }
}
