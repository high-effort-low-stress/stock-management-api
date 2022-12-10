package com.github.hels.stockmanagement.controller;

import com.github.hels.stockmanagement.controller.mappers.CategoryMapper;
import com.github.hels.stockmanagement.docs.CreateCategoryApi;
import com.github.hels.stockmanagement.docs.GetCategoryApi;
import com.github.hels.stockmanagement.dto.CreateCategoryDTO;
import com.github.hels.stockmanagement.dto.GetCategoryDTO;
import com.github.hels.stockmanagement.model.Category;
import com.github.hels.stockmanagement.service.CreateCategoryService;
import com.github.hels.stockmanagement.service.GetCategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/categories")
@Tag(name = "Category", description = "API de gerência de categorias de produtos")
public class CategoryController {
    private final CreateCategoryService createCategoryService;
    private final GetCategoryService getCategoryService;
    private final CategoryMapper categoryMapper;

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

    @GetCategoryApi
    @GetMapping("/{categoryUuid}")
    public GetCategoryDTO.Response getCategory(@PathVariable String categoryUuid, HttpServletResponse response) {
        Category category = getCategoryService.execute(categoryUuid);
        if (category == null) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
        }
        return categoryMapper.toCategoryDTO(category);
    }


}
