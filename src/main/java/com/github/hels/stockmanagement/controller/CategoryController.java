package com.github.hels.stockmanagement.controller;

import com.github.hels.stockmanagement.docs.CreateCategoryApi;
import com.github.hels.stockmanagement.dto.CreateCategoryDTO;
import com.github.hels.stockmanagement.model.Category;
import com.github.hels.stockmanagement.service.CreateCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/categories")
@Tag(name = "Category", description = "API de gerência de categorias de produtos")
public class CategoryController {
    private final CreateCategoryService createCategoryService;
    @PostMapping
    @CreateCategoryApi
    public CreateCategoryDTO.Response createCategory(
            @Valid @RequestBody CreateCategoryDTO.Request requestBody
    ) {
        String name = requestBody.getName();
        String parentUuid = requestBody.getParentUuid();
        Category category =  createCategoryService.execute(name, parentUuid);

        return new CreateCategoryDTO.Response(category.getUuid());
    }
}
