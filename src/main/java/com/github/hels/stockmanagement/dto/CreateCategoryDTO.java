package com.github.hels.stockmanagement.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;


@Getter
@Setter
@NoArgsConstructor
public class CreateCategoryDTO {

    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request {
        @NotEmpty
        private String name;
        private String parentUuid;
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
