package com.github.hels.stockmanagement.dto;

import com.github.hels.stockmanagement.http.pagination.PageInput;
import lombok.*;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ListCategoryDTO {
    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Request extends PageInput {

        @Size(min = 1, max = 40)
        private String name;
        private String uuid;
        @Pattern(regexp = "name", message = "sort deve estar entre as opções: [name]")
        private String sortBy = "name";

    }

    @ToString
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private String name;
        private String uuid;
    }
}
