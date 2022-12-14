package com.github.hels.stockmanagement.docs.schemas;

import com.github.hels.stockmanagement.http.pagination.PageOutput;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ListCategorySchema {
    @Getter
    @Setter
    @Schema(name = "ListCategoryResponse")
    public static class Response extends PageOutput<Response.CategoryDTO> {

        @Getter
        @Setter
        @NoArgsConstructor
        public static class CategoryDTO {
            @Schema(description = "UUID da categoria", example = "d08ca46a-f391-49e6-9226-d4beb5f187b1")
            private String uuid;
            @Schema(description = "Nome da Categoria", example = "CPU")
            private String name;
        }
    }
}
