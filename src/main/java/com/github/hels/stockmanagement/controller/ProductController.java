package com.github.hels.stockmanagement.controller;

import com.github.hels.stockmanagement.controller.mappers.ProductMapper;
import com.github.hels.stockmanagement.docs.CreateProductApi;
import com.github.hels.stockmanagement.docs.GetProductApi;
import com.github.hels.stockmanagement.dto.CreateProductDTO;
import com.github.hels.stockmanagement.dto.GetProductDTO;
import com.github.hels.stockmanagement.model.Product;
import com.github.hels.stockmanagement.service.CreateProductService;
import com.github.hels.stockmanagement.service.GetProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/products")
@Tag(name = "Product", description = "API para cadastro de produtos")
public class ProductController {
    private final CreateProductService createProductService;
    private final GetProductService getProductService;
    private final ProductMapper productMapper;
    @CreateProductApi
    @PostMapping
    public CreateProductDTO.Response product(
            @Valid @RequestBody CreateProductDTO.Request requestBody
    ) {
        String name = requestBody.getName();
        String description = requestBody.getDescription();
        String categoryUuid = requestBody.getCategoryUuid();

        Product product = createProductService.execute(name, description, categoryUuid);

        return new ProductMapper().mapToCreateProductDto(product);
    }
    @GetProductApi
    @GetMapping("/{productUuid}")
    public GetProductDTO.Response getProduct (
            @PathVariable String productUuid, HttpServletResponse response) {
        Product product = getProductService.execute(productUuid);
        if (product == null) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
        }
        return productMapper.mapToGetProductDto(product);
    }
}
