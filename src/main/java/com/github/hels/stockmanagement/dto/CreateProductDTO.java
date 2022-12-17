package com.github.hels.stockmanagement.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CreateProductDTO {
    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        private String name;
        private String description;
        private String categoryUuid;


    }
    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private String uuid;
    }
}
