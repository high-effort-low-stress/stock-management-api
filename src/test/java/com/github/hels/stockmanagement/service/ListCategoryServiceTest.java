package com.github.hels.stockmanagement.service;

import com.github.hels.stockmanagement.dto.input.ListCategoryInputDTO;
import com.github.hels.stockmanagement.http.pagination.PageInput;
import com.github.hels.stockmanagement.http.pagination.PageOutput;
import com.github.hels.stockmanagement.model.Category;
import com.github.hels.stockmanagement.repository.ICategoryRepository;
import com.github.hels.stockmanagement.repository.specification.ListCategorySpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListCategoryServiceTest {
    ICategoryRepository repository = mock(ICategoryRepository.class);

    ListCategoryService service = new ListCategoryService(repository);

    @Test
    @DisplayName("should return category")
    void when_getCategory_then_returnCategory() {

        Category category = new Category();
        category.setName("name");
        category.setUuid("uuid");

        Page<Category> page = new PageImpl<>(List.of(category));

        when(repository.findAll(any(ListCategorySpecification.class), any(Pageable.class))).thenReturn(page);

        ListCategoryInputDTO listCategoryInputDTO = new ListCategoryInputDTO();
        listCategoryInputDTO.setUuid("uuid");
        listCategoryInputDTO.setName("name");

        PageInput pageInput = new PageInput();
        PageOutput<Category> result = service.execute(listCategoryInputDTO, pageInput);

        Assertions.assertEquals("name", result.getData().get(0).getName());
        Assertions.assertEquals("uuid", result.getData().get(0).getUuid());
    }
}
