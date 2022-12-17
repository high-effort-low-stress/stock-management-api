package com.github.hels.stockmanagement.docs.schemas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


public class CreateProductSchema {
    @Getter
    @Setter
    @Schema(name = "CreateProductRequest")
    public static class Request {
        @Schema(description = "Nome do Produto", example = "Ryzen 5 5600")
        private String name;
        @Schema(description = "Descrição do Produto", example = "Processador AMD Ryzen 5 5600")
        private String description;
        @Schema(description = "Quantas unidades do produto serão cadastradas", example = "1")
        private Integer quantity;
        @Schema(description = "Uuid da categoria ao qual o produto pertence", example = "d08ca46a-f391-49e6-9226-d4beb5f187b1")
        private String categoryUuid;

    }

    @Getter
    @Setter
    @Schema(name = "CreateProductResponse")
    public static class Response {
        @Schema(description = "UUID do produto cadsatrado", example = "a42129bb-b228-4059-aecd-659ebe751afe")
        private String uuid;
    }
}
