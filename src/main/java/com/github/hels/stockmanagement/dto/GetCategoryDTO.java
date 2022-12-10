package com.github.hels.stockmanagement.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class GetCategoryDTO {
    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @NotNull
    public static class Response {
        private String uuid;
        private String name;
        private Set<Response> children;
    }
}

