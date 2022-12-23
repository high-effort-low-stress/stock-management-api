package com.github.hels.stockmanagement.controller.mappers;

import com.github.hels.stockmanagement.dto.CreateProductDTO;
import com.github.hels.stockmanagement.dto.GetProductDTO;
import com.github.hels.stockmanagement.model.Category;
import com.github.hels.stockmanagement.model.Product;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductMapper {
    public CreateProductDTO.Response createProductToDTO(Product p) {
        if (p == null)
            return null;

        CreateProductDTO.Response dto = new CreateProductDTO.Response();

        dto.setUuid(p.getUuid());

        return dto;
    }

    public GetProductDTO.Response getProductToDTO(Product p) {
        if (p == null)
            return null;
        GetProductDTO.Response dto = new GetProductDTO.Response();

        dto.setUuid(p.getUuid());
        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        dto.setCategory(p.getCategories().stream().map(Category::getName).collect(Collectors.toSet()));
        dto.setQuantity(p.getQuantity());
        dto.setCreatedAt(p.getCreatedAt());

        return dto;
    }
}

