package com.github.hels.stockmanagement.service;

import com.github.hels.stockmanagement.exceptions.ApiException;
import com.github.hels.stockmanagement.model.Category;
import com.github.hels.stockmanagement.repository.ICategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

class CreateCategoryServiceTest {

    ICategoryRepository repository = mock(ICategoryRepository.class);
    CreateCategoryService service = new CreateCategoryService(repository);

    @Test
    @DisplayName("should create category without parent")
    void when_validInput_then_createCategory() {
        Category category = new Category();
        category.setName("name");

        when(repository.save(any(Category.class))).thenReturn(category);

        Category result = service.execute("name", null);

        Assertions.assertEquals("name", result.getName());
    }

    @Test
    @DisplayName("should create category with parent")
    void when_validInputWitUuid_then_createCategory() {
        Category parentCategory = new Category();
        parentCategory.setName("parent");
        parentCategory.setUuid("uuid");

        Category category = new Category();
        category.setName("name");
        category.setParent(parentCategory);

        Optional<Category> optionalCategory = Optional.of(parentCategory);

        when(repository.save(any(Category.class))).thenReturn(category);
        when(repository.findByUuid(any(String.class))).thenReturn(optionalCategory);

        Category result = service.execute("name", "uuid");

        Assertions.assertEquals("name", result.getName());
        Assertions.assertEquals("uuid", result.getParent().getUuid());
    }

    @Test
    @DisplayName("should not create category with inexistent parent")
    void when_inexistentParent_then_dontCreateCategory() {
        Optional<Category> inexistentCategory = Optional.empty();

        when(repository.findByUuid(any(String.class))).thenReturn(inexistentCategory);

        ApiException ex = Assertions.assertThrows(ApiException.class, () -> {
            service.execute("name", "null");
        });

        Assertions.assertEquals("Parent category not found", ex.getMessage());
    }

    @Test
    @DisplayName("should not create category with duplicated name")
    void when_duplicatedName_then_dontCreateCategory(){
        String parentUuid = "parentUuid";
        String categoryName = "expectedCategory";

        Category parentCategory = new Category();
        parentCategory.setName("parent");
        parentCategory.setUuid(parentUuid);

        Category duplicatedCategory = new Category();
        duplicatedCategory.setName("name");
        duplicatedCategory.setParent(parentCategory);

        Optional<Category> optionalDuplicatedCategory = Optional.of(duplicatedCategory);
        Optional<Category> optionalParentCategory = Optional.of(parentCategory);

        Category expectedCategory = new Category();
        expectedCategory.setName(categoryName);
        expectedCategory.setParent(parentCategory);

        when(repository.findByUuid(any(String.class))).thenReturn(optionalParentCategory);
        when(repository.findByNameAndParentUuid(anyString(),anyString()))
                .thenReturn(optionalDuplicatedCategory);

        ApiException ex = Assertions.assertThrows(ApiException.class, () -> {
            service.execute(categoryName, parentUuid);
        });

        Assertions.assertEquals("A category with same name already exists", ex.getMessage());

    }
}
