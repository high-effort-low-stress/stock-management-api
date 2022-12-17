package com.github.hels.stockmanagement.repository.input;

import com.github.hels.stockmanagement.repository.specification.Criteria;
import com.github.hels.stockmanagement.repository.specification.Operator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class ListCategoryInput {
    private final List<Criteria> rootFields = new ArrayList<>();
    private final List<Criteria> parentFields = new ArrayList<>();

    public ListCategoryInput addRootField(String field, Operator operator, Object value){
        rootFields.add(new Criteria(field, operator, value));
        return this;
    }

    public ListCategoryInput addParentField(String field, Operator operator, Object value){
        parentFields.add(new Criteria(field, operator, value));
        return this;
    }
}
