package com.github.hels.stockmanagement.service;

import com.github.hels.stockmanagement.model.Product;
import com.github.hels.stockmanagement.repository.IProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetProductService {
    private final IProductRepository repository;

    public Product execute(String uuid) {
        return repository
                .findByUuid(uuid)
                .orElse(null);
    }
}
