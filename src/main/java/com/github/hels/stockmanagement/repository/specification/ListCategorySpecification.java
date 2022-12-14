package com.github.hels.stockmanagement.repository.specification;

import com.github.hels.stockmanagement.model.Category;
import com.github.hels.stockmanagement.repository.input.ListCategoryInput;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashSet;
import java.util.Set;

public record ListCategorySpecification (
        ListCategoryInput listCategoryInput
) implements Specification<Category>, ICriteriaHandler {

    @Override
    public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        Set<Predicate> predicates = new HashSet<>();

        addPredicates(predicates, listCategoryInput.getRootFields(), root, builder);
        addPredicates(predicates, listCategoryInput.getParentFields(), root.join("parent"), builder);

        query.distinct(true);
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
