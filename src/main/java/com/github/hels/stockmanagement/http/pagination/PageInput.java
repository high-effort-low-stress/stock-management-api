package com.github.hels.stockmanagement.http.pagination;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PageInput {
    @Parameter
    private Integer offset = 0;
    @Parameter
    private Integer limit = 10;
    @Parameter
    private String sortBy = "createdAt";
    @Parameter
    private OrderByEnum orderBy = OrderByEnum.DESC;
}
