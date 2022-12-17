package com.github.hels.stockmanagement.controller.mappers;

import com.github.hels.stockmanagement.dto.CreateProductDTO;
import com.github.hels.stockmanagement.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public CreateProductDTO.Request requestToDTO (Product p) {
        if (p == null)
            return null;

        CreateProductDTO.Request dto = new CreateProductDTO.Request();

        dto.setName(p.getName());
        dto.setDescription(p.getDescription());
        dto.setQuantity(p.getQuantity());
        dto.setCategoryUuid(p.getCategories().toString());
        return dto;
    }
    public CreateProductDTO.Response responseToDTO (Product p) {
        if (p == null)
            return null;

        CreateProductDTO.Response dto = new CreateProductDTO.Response();

        dto.setUuid(p.getUuid());

        return dto;
    }
}
