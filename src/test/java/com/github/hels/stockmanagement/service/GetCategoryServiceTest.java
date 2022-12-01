package com.github.hels.stockmanagement.service;

import com.github.hels.stockmanagement.exceptions.ApiException;
import com.github.hels.stockmanagement.model.Category;
import com.github.hels.stockmanagement.repository.ICategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

public class GetCategoryServiceTest {

    ICategoryRepository repository = mock(ICategoryRepository.class);

    GetCategoryService service = new GetCategoryService(repository);

    @Test
    @DisplayName("should return category")
    void when_getCategory_then_returnCategory() {
        Category childCategory = new Category();
        childCategory.setName("childName");
        childCategory.setUuid("childUuid");

        Set<Category> childrenCategory = new HashSet<>();
        childrenCategory.add(childCategory);

        Category category = new Category();
        category.setName("name");
        category.setUuid("uuid");
        category.setChildren(childrenCategory);

        Optional<Category> optionalCategory = Optional.of(category);

        when(repository.findByUuid(anyString())).thenReturn(optionalCategory);

        Category result = service.execute("uuid");

        Assertions.assertEquals("name", result.getName());
        Assertions.assertEquals("uuid", result.getUuid());
        Assertions.assertEquals("childName", result.getChildren().stream().toList().get(0).getName());
        Assertions.assertEquals("childUuid", result.getChildren().stream().toList().get(0).getUuid());
    }

    @Test
    @DisplayName("should not return category that does not exists in repository")
    void when_invalidCategory_then_dontReturnCategory() {
        String categoryUuid = "expectedCategoryUuid";

        Category category = new Category();

        category.setUuid(categoryUuid);

        Optional<Category> optionalCategory = Optional.of(category);

        when(repository.findByUuid(anyString())).thenReturn(optionalCategory);
        //TODO: finish this test
        ApiException ex = Assertions.assertThrows(ApiException.class, () -> service.execute(categoryUuid));

        Assertions.assertEquals("Category not found", ex.getMessage());
    }
}
