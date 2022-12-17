package com.github.hels.stockmanagement.service;

import com.github.hels.stockmanagement.exceptions.ApiException;
import com.github.hels.stockmanagement.model.Category;
import com.github.hels.stockmanagement.model.Product;
import com.github.hels.stockmanagement.repository.ICategoryRepository;
import com.github.hels.stockmanagement.repository.IProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CreateProductServiceTest {

    IProductRepository mockProductRepository = mock(IProductRepository.class);
    ICategoryRepository mockCategoryRepository = mock(ICategoryRepository.class);
    CreateProductService service = new CreateProductService(mockProductRepository, mockCategoryRepository);

    @Test
    @DisplayName("Should create product")
    void when_validInput_then_createProduct() {
        Category category = new Category();
        category.setName("categoryName");
        category.setUuid("categoryUuid");

        Optional<Category> optionalCategory = Optional.of(category);

        Product product = new Product();
        product.setName("name");
        product.setDescription("description");
        product.setQuantity(1);
        product.setCategories(Set.of(category));
        product.setUuid("uuid");

        when(mockCategoryRepository.findByUuid(anyString())).thenReturn(optionalCategory);
        when(mockProductRepository.save(any(Product.class))).thenReturn(product);

        Product result = service.execute("name", "description", 1, "categoryUuid");

        Assertions.assertEquals("uuid", result.getUuid());
    }

    @Test
    @DisplayName("Should not create prodduct with non existent category")
    void when_nonExistentCateogry_then_dontCreateProduct() {
        Category category = new Category();
        category.setName("categoryName");
        category.setUuid("categoryUuid");

        Optional<Category> optionalCategory = Optional.empty();

        Product product = new Product();
        product.setName("name");
        product.setDescription("description");
        product.setQuantity(1);
        product.setCategories(Set.of(category));
        product.setUuid("uuid");

        when(mockCategoryRepository.findByUuid(anyString())).thenReturn(optionalCategory);
        when(mockProductRepository.save(any(Product.class))).thenReturn(product);

        ApiException ex = Assertions.assertThrows(ApiException.class, () -> {
            service.execute("name", "description", 1, "categoryUuid");
        });

        Assertions.assertEquals("Category does not exists", ex.getMessage());
    }

    @Test
    @DisplayName("Should not create product with duplicated name")
    void when_duplicatedProductName_then_dontCreateProduct() {
        String productName = "expectedProductName";
        String productDescription = "description";
        Integer productQuantity = 1;

        Category category = new Category();
        category.setName("categoryName");
        category.setUuid("categoryUuid");

        Product duplicatedProduct = new Product();
        duplicatedProduct.setName("duplicatedProduct");

        Optional<Category> optionalCategory = Optional.of(category);
        Optional<Product> optionalDuplicatedProduct = Optional.of(duplicatedProduct);

        Product ExpectedProduct = new Product();
        ExpectedProduct.setName(productName);
        ExpectedProduct.setDescription(productDescription);
        ExpectedProduct.setQuantity(productQuantity);
        ExpectedProduct.setCategories(Set.of(category));

        when(mockCategoryRepository.findByUuid(anyString())).thenReturn(optionalCategory);
        when(mockProductRepository.findByName(anyString())).thenReturn(optionalDuplicatedProduct);

        ApiException ex = Assertions.assertThrows(ApiException.class, () -> {
            service.execute("name", "description", 1, "categoryUuid");
        });

        Assertions.assertEquals("A product with same name already exists", ex.getMessage());
    }
}
