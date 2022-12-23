package com.github.hels.stockmanagement.docs.schemas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

public class GetProductSchema {
    @Getter
    @Setter
    @Schema(name = "GetProductResponse")
    public static class Response {
        @Schema(description = "UUID do produto", example = "d08ca46a-f391-49e6-9226-d4beb5f187b1")
        private String uuid;
        @Schema(description = "Nome do produto", example = "AMD Ryzen 5 5600")
        private String name;
        @Schema(description = "Descrição técnica do produto", example = "Processador am4 com 6 nucleos e 12 threads")
        private String description;
        @Schema(description = "quantidade disponível do produto", example = "1")
        private Integer quantity;
        @Schema(description = "Categorias e/ou subcategorias do protudo")
        private Set<String> categories;

        @Getter
        @Setter
        @Schema(name = "GetProductResponseCategories")
        public static class Categories {
            @Schema(description = "Nome das categorias e/ou sbucategorias", example = "AMD")
            private String category;
        }
    }
}