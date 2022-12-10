package com.github.hels.stockmanagement.docs.schemas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


public class GetCategorySchema {
    @Getter
    @Setter
    @Schema(name = "GetCategoryResponse")
    public static class Response {
        @Schema(description = "UUID da categoria", example = "d08ca46a-f391-49e6-9226-d4beb5f187b1")
        private String uuid;
        @Schema(description = "Nome da Categoria", example = "CPU")
        private String name;

        @Schema(description = "Categorias filhas")
        private Set<Child> children;

        @Getter
        @Setter
        @Schema(name = "GetCategoryResponseChild")
        public static class Child {
            @Schema(description = "UUID da Sub-categoria", example = "d08ca46a-f391-49e6-9226-d4beb5f187b1")
            private String uuid;
            @Schema(description = "Nome da Sub-categoria", example = "AMD")
            private String name;
        }
    }

}
