package com.github.hels.stockmanagement.repository.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public interface ICriteriaHandler {
    default void addPredicates(Set<Predicate> predicates, List<Criteria> criteria, From<?, ?> from, CriteriaBuilder builder) {
        if (!criteria.isEmpty()) {
            for (Criteria c : criteria) {
                if (Objects.nonNull(c.value())) {
                    Operator operator = Operator.valueOf(c.operator().name());
                    predicates.add(operator.getPredicate(from, builder, c.field(), c.value()));
                }
            }
        }
    }
}
