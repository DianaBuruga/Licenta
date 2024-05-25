package com.ulbs.careerstartup.specification;

import com.ulbs.careerstartup.specification.entity.SearchCriteria;
import jakarta.persistence.criteria.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder
public class GenericSpecification<T> implements Specification<T> {
    private List<SearchCriteria> criteriaList;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : criteriaList) {
            switch (criteria.getOperation()) {
                case ">" -> predicates.add(handleGreaterThanForDifferentDataTypes(criteria, builder, root));
                case "<" -> predicates.add(handleLessThanForDifferentDataTypes(criteria, builder, root));
                case "=" -> predicates.add(handleEqualsForDifferentDataTypes(criteria, builder, root));
                default -> throw new IllegalArgumentException("Invalid operation: " + criteria.getOperation());
            }
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }

    private Predicate handleEqualsForDifferentDataTypes (SearchCriteria criteria, CriteriaBuilder builder, Root<T> root) {
        Predicate predicate;
        String[] keys = criteria.getKey().split("\\.");
        String value = criteria.getValue().toString();

        if (keys.length > 1) {
            Join<Object, Object> join = root.join(keys[0]);
            predicate = buildPredicate(join.get(keys[1]), value, builder);
        } else {
            predicate = buildPredicate(root.get(criteria.getKey()), value, builder);
        }

        return predicate;
    }

    private Predicate buildPredicate(Path<Object> path, String value, CriteriaBuilder builder) {
        Predicate predicate;

        try {
            predicate = builder.equal(path, UUID.fromString(value));
        } catch (IllegalArgumentException e) {
            try {
                predicate = builder.equal(path, Long.parseLong(value));
            } catch (IllegalArgumentException ex) {
                predicate = builder.like(path.as(String.class), "%" + value + "%");
            }
        }
        return predicate;
    }

    private Predicate handleGreaterThanForDifferentDataTypes(SearchCriteria criteria, CriteriaBuilder builder, Root<T> root) {
        String value = criteria.getValue().toString();
        Predicate predicate;

        try {
            predicate = builder.greaterThanOrEqualTo(root.get(criteria.getKey()), Long.parseLong(value));
        } catch (IllegalArgumentException e) {
            predicate = builder.like(root.get(criteria.getKey()), "%" + value + "%");
        }

        return predicate;
    }

    private Predicate handleLessThanForDifferentDataTypes(SearchCriteria criteria, CriteriaBuilder builder, Root<T> root) {
        String value = criteria.getValue().toString();
        Predicate predicate;

        try {
            predicate = builder.lessThanOrEqualTo(root.get(criteria.getKey()), Long.parseLong(value));
        } catch (IllegalArgumentException e) {
            predicate = builder.like(root.get(criteria.getKey()), "%" + value + "%");
        }

        return predicate;
    }

}
