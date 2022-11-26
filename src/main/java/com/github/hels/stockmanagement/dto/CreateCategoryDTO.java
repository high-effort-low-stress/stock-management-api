package com.github.hels.stockmanagement.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Builder
@Getter
@Setter
@NoArgsConstructor
public class CreateCategoryDTO {

    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @NotNull
    public static class Request{
        @NotEmpty
        private String name;
        private String parentUuid;
    }

    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        private String uuid;
    }
}