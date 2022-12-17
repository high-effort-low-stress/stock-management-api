package com.github.hels.stockmanagement.controller.mappers;

import com.github.hels.stockmanagement.dto.CreateProductDTO;
import com.github.hels.stockmanagement.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public CreateProductDTO.Response responseToDTO (Product p) {
        if (p == null)
            return null;

        CreateProductDTO.Response dto = new CreateProductDTO.Response();

        dto.setUuid(p.getUuid());

        return dto;
    }
}
