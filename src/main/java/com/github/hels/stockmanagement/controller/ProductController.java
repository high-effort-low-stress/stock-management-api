package com.github.hels.stockmanagement.controller;

import com.github.hels.stockmanagement.controller.mappers.ProductMapper;
import com.github.hels.stockmanagement.docs.CreateProductApi;
import com.github.hels.stockmanagement.dto.CreateProductDTO;
import com.github.hels.stockmanagement.model.Product;
import com.github.hels.stockmanagement.service.CreateProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/products")
@Tag(name = "Product", description = "API para cadastro de produtos")
public class ProductController {
    private final CreateProductService createProductService;
    @PostMapping
    @CreateProductApi
    public CreateProductDTO.Response product(
            @Valid @RequestBody CreateProductDTO.Request requestBody
    ) {
        String name = requestBody.getName();
        String description = requestBody.getDescription();
        Integer quantity = requestBody.getQuantity();
        String categoryUuid = requestBody.getCategoryUuid();

        Product product = createProductService.execute(name, description, quantity, categoryUuid);

        return new ProductMapper().responseToDTO(product);
    }
}
