package com.github.hels.stockmanagement.docs.schemas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


public class CreateCategorySchema {
    @Getter
    @Setter
    public static class Request{
        @Schema(description = "Nome da Categoria", example = "CPU")
        private String name;
        @Schema(description = "UUID da categoria-mãe", example = "d08ca46a-f391-49e6-9226-d4beb5f187b1")
        private String parentUuid;
    }

    @Getter
    @Setter
    public static class Response{
        @Schema(description = "UUID da categoria cadastrada", example = "a42129bb-b228-4059-aecd-659ebe751afe")
        private String uuid;
    }
}
