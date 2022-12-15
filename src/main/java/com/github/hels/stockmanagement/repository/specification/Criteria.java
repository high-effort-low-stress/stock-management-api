package com.github.hels.stockmanagement.repository.specification;

public record Criteria(String field, Operator operator, Object value) {
}
