package com.github.hels.stockmanagement.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListCategoryInputDTO {
    private String name;
    private String uuid;
}
