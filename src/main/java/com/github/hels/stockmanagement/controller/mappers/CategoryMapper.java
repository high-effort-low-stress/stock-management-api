package com.github.hels.stockmanagement.controller.mappers;

import com.github.hels.stockmanagement.dto.GetCategoryDTO;
import com.github.hels.stockmanagement.model.Category;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryMapper {
    public GetCategoryDTO.Response toCategoryDTO(Category c) {
        if (c == null){
            return null;
        }

        GetCategoryDTO.Response dto = new GetCategoryDTO.Response();
        dto.setName(c.getName());
        dto.setUuid(c.getUuid());
        dto.setChildren(c.getChildren().stream().map(this::toCategoryDTO).collect(Collectors.toSet()));
        return dto;
    }
}
