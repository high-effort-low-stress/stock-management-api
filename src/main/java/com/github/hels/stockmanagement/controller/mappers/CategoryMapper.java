package com.github.hels.stockmanagement.controller.mappers;

import com.github.hels.stockmanagement.dto.GetCategoryDTO;
import com.github.hels.stockmanagement.dto.ListCategoryDTO;
import com.github.hels.stockmanagement.dto.input.ListCategoryInputDTO;
import com.github.hels.stockmanagement.http.pagination.PageInput;
import com.github.hels.stockmanagement.model.Category;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    public GetCategoryDTO.Response toCategoryDTO(Category c) {
        if (c == null)
            return null;

        GetCategoryDTO.Response dto = new GetCategoryDTO.Response();
        dto.setName(c.getName());
        dto.setUuid(c.getUuid());
        dto.setChildren(c.getChildren().stream().map(this::toCategoryDTO).collect(Collectors.toSet()));
        return dto;
    }

    public PageInput toPageInput(ListCategoryDTO.Request category){
        PageInput input = new PageInput();
        input.setLimit(category.getLimit());
        input.setOffset(category.getOffset());
        input.setOrderBy(category.getOrderBy());
        input.setSortBy(category.getSortBy());
        return input;
    }

    public ListCategoryInputDTO toListCategoryInput(ListCategoryDTO.Request category){
        ListCategoryInputDTO input = new ListCategoryInputDTO();
        input.setName(category.getName());
        input.setUuid(category.getUuid());
        return input;
    }

    public ListCategoryDTO.Response toListCategoryDTO(Category c) {
        if (c == null){
            return null;
        }

        ListCategoryDTO.Response dto = new ListCategoryDTO.Response();
        dto.setName(c.getName());
        dto.setUuid(c.getUuid());
        return dto;
    }
}
