package com.github.hels.stockmanagement.service;

import com.github.hels.stockmanagement.model.Category;
import com.github.hels.stockmanagement.model.Product;
import com.github.hels.stockmanagement.repository.IProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetProductServiceTest {
     IProductRepository mockProductRepository = mock(IProductRepository.class);
     GetProductService service = new GetProductService(mockProductRepository);

     @Test
     @DisplayName("Should return product")
     void when_getProduct_then_returnProduct() {
          LocalDateTime createdAt = LocalDateTime.now();

          Category category = new Category();
          category.setName("category name");
          category.setUuid("category uuid");

          Set<Category> categories = new HashSet<>();
          categories.add(category);

          Product product = new Product();
          product.setUuid("uuid");
          product.setName("name");
          product.setDescription("description");
          product.setCategories(categories);
          product.setQuantity(1);
          product.setCreatedAt(createdAt);

          Optional<Product> optionalProduct = Optional.of(product);

          when(mockProductRepository.findByUuid(anyString())).thenReturn(optionalProduct);

          Product result = service.execute("uuid");

          Assertions.assertEquals("name", result.getName());
          Assertions.assertEquals("uuid", result.getUuid());
          Assertions.assertEquals("description", result.getDescription());
          Assertions.assertEquals(1, result.getQuantity());
          Assertions.assertEquals("category name", result.getCategories().stream().toList().get(0).getName());
          Assertions.assertEquals(createdAt, result.getCreatedAt());
     }
     @Test
     @DisplayName("Should not return Product that does not exists in repository")
     void when_invalidProduct_then_doNotReturnProduct() {
          Optional<Product> nonExistentProduct = Optional.empty();

          when(mockProductRepository.findByUuid(anyString())).thenReturn(nonExistentProduct);

          Product result = service.execute("nonExistent");

          Assertions.assertNull(result);
     }
}
