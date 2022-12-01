package com.github.hels.stockmanagement.controller;

import com.github.hels.stockmanagement.docs.CreateCategoryApi;
import com.github.hels.stockmanagement.dto.CreateCategoryDTO;
import com.github.hels.stockmanagement.dto.GetCategoryDTO;
import com.github.hels.stockmanagement.model.Category;
import com.github.hels.stockmanagement.service.CreateCategoryService;
import com.github.hels.stockmanagement.service.GetCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/categories")
@Tag(name = "Category", description = "API de gerência de categorias de produtos")
public class CategoryController {
    private final CreateCategoryService createCategoryService;
    private final GetCategoryService getCategoryService;

    @PostMapping
    @CreateCategoryApi
    public CreateCategoryDTO.Response createCategory(
            @Valid @RequestBody CreateCategoryDTO.Request requestBody
    ) {
        String name = requestBody.getName();
        String parentUuid = requestBody.getParentUuid();
        Category category = createCategoryService.execute(name, parentUuid);

        return new CreateCategoryDTO.Response(category.getUuid());
    }

    @GetMapping("/{categoryUuid}")
    public GetCategoryDTO getCategory(@PathVariable String categoryUuid) {
        Category category = getCategoryService.execute(categoryUuid);
        GetCategoryDTO response = new GetCategoryDTO();
        response.setName(category.getName());
        response.setUuid(category.getUuid());
        response.setChildren(category.getChildren().stream().map(this::toCategoryDTO).collect(Collectors.toSet()));
        return response;
    }

    private GetCategoryDTO toCategoryDTO(Category c) {
        GetCategoryDTO dto = new GetCategoryDTO();
        dto.setName(c.getName());
        dto.setUuid(c.getUuid());
        return dto;
    }
}
