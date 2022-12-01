package com.github.hels.stockmanagement.dto;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class GetCategoryDTO {
    private String uuid;
    private String name;
    private Set<GetCategoryDTO> children;

}

