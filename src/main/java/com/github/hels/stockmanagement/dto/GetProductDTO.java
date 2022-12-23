package com.github.hels.stockmanagement.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class GetProductDTO {
    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @NotNull
    public static class Response {
        private String uuid;
        private String name;
        private String description;
        private Integer quantity;
        private Set<String> category;
        private LocalDateTime createdAt;
    }
}
